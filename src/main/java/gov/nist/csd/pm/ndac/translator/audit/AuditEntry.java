package gov.nist.csd.pm.ndac.translator.audit;

public class AuditEntry {

    private String user;
    private String originalSql;
    private String permittedSql;
    private double time;

    public AuditEntry() {}

    public AuditEntry(String user, String originalSql, String permittedSql, double time) {
        this.user = user;
        this.originalSql = originalSql;
        this.permittedSql = permittedSql;
        this.time = time;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
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
