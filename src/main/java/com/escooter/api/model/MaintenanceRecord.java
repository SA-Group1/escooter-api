package com.escooter.api.model;
import java.util.Date;
public class MaintenanceRecord {
    private String escooterId;
    private String issues;
    private int fees;
    private String addr;
    private String repairman;
    private Date date;
    private String remark;

    public String getEscooterId(){
        return escooterId;
    }

    public String getIssues(){
        return issues;
    }

    public int gerFees(){
        return fees;
    }

    public String getAddr(){
        return addr;
    }

    public String getRepairman(){
        return repairman;
    }

    public Date getDate(){
        return date;
    }
    
    public String getRemark(){
        return remark;
    }
}
