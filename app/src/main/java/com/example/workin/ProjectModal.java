package com.example.workin;

public class ProjectModal {
    private int id;
    private String Project_name;

    public int getId() { return id; }
    public void setId( int id ) { this.id = id; }

    public String getProject_name() { return Project_name; }
    public void setProject_name( String project_name ) { this.Project_name = Project_name; }

    public ProjectModal(int id, String project_name){
        this.id = id;
        this.Project_name = project_name;
    }

    public ProjectModal(String project_name){
        this.Project_name = project_name;
    }
}
