package gov.nist.csd.pm.ndac.translator.audit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/audit")
public class AuditController {

    private List<AuditEntry> auditLog;

    public AuditController(List<AuditEntry> auditLog) {
        this.auditLog = auditLog;
    }

    @GetMapping
    public AuditResponse getAuditLog() {
        return new AuditResponse(auditLog);
    }

    static class AuditResponse {
        private List<AuditEntry> audit;

        public AuditResponse() {
        }

        public AuditResponse(List<AuditEntry> audit) {
            this.audit = audit;
        }

        public List<AuditEntry> getAudit() {
            return audit;
        }

        public void setAudit(List<AuditEntry> audit) {
            this.audit = audit;
        }
    }

}
