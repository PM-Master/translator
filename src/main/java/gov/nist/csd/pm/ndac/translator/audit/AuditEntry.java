package gov.nist.csd.pm.ndac.translator.audit;

import gov.nist.csd.pm.pdp.services.UserContext;

public class AuditEntry {

    private UserContext user;
    private String originalSql;
    private String permittedSql;
    private double time;

    public AuditEntry() {}

    public AuditEntry(UserContext user, String originalSql, String permittedSql, double time) {
        this.user = user;
        this.originalSql = originalSql;
        this.permittedSql = permittedSql;
        this.time = time;
    }

    public UserContext getUser() {
        return user;
    }

    public void setUser(UserContext user) {
        this.user = user;
    }

    public String getOriginalSql() {
        return originalSql;
    }

    public void setOriginalSql(String originalSql) {
        this.originalSql = originalSql;
    }

    public String getPermittedSql() {
        return permittedSql;
    }

    public void setPermittedSql(String permittedSql) {
        this.permittedSql = permittedSql;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }
}
