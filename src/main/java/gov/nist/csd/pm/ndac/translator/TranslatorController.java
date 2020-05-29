package gov.nist.csd.pm.ndac.translator;

import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pdp.PDP;
import gov.nist.csd.pm.pdp.services.UserContext;
import ndac.NDACEngine;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/translate")
public class TranslatorController {

    private NDACEngine ndacEngine;
    private String schema;

    public TranslatorController(PDP pdp) throws PMException {
        ndacEngine = new NDACEngine(pdp, new NDACEngine.Options(false, true));
    }

    @PutMapping("/schema/{schema}")
    public void setSchema(@PathVariable String schema) {
        this.schema = schema;
    }

    /**
     * Translate the sql provide in the request to permitted sql based on the user and process information that is appended
     * as a comment to the beginning of the query.
     * @param request the request object holding the query
     * @return the permitted sql
     * @throws PMException if no user or process is provided or if there is an error with the NGAC engine
     * @throws JSQLParserException if there is an error with the query
     */
    @PostMapping
    public Response translate(@RequestBody Request request) throws PMException, JSQLParserException {
        String sql = request.getSql();
        if (!sql.startsWith("/*!")) {
            throw new PMException("must provide user in query comment (/*!user=<username>*/select * from ...)");
        }

        // extract user and process information
        String user = null;
        String process = null;
        String[] pieces = sql.split("/\\*!|\\*//*");
        if(pieces.length > 1) {
            String[] properties = pieces[1].split(" ");
            for(String s : properties){
                if(s.startsWith("user=")){
                    user = s.split("=")[1];
                } else if (s.startsWith("process")){
                    process = s.split("=")[1];
                }
            }
        }

        // throw an exception if no user or process is provided
        if (user == null && process == null) {
            throw new PMException("no user or process information available in query comment");
        }

        // translate the sql
        sql = ndacEngine.translate(new UserContext(user, process), this.schema, sql);
        return new Response(sql);
    }

    static class Request {
        private String sql;

        public String getSql() {
            return sql;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }
    }

    static class Response {
        private String sql;

        public Response(String sql) {
            this.sql = sql;
        }

        public String getSql() {
            return sql;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }
    }

}
