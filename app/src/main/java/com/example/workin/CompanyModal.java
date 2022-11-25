package com.example.workin;

public class CompanyModal {
    private int id;
    private String Company_name;

    public int getId() { return id; }
    public void setId( int id ) { this.id = id; }

    public String getCompany_name() { return Company_name; }
    public void setCompany_name( String company_name ) { this.Company_name = company_name; }

    public CompanyModal(int id, String company_name){
        this.id = id;
        this.Company_name = company_name;
    }

    public CompanyModal(String company_name){
        this.Company_name = company_name;
    }
}
