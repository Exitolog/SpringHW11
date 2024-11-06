package ru.gb.timesheet.model;

public enum RoleName {
    ADMIN("admin"), USER("user"), REST("rest");

    private String name;


    RoleName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
