package com.stackd.stackd.db.entities;

public class Recruiter  {
    /* Builder to populate the fields of a Recruiter object */
    public static class Builder {
        private String recId;
        private String compId;
        private String firstName;
        private String lastName;
        private String email;

        public Builder recId(String recId) {
            this.recId = recId;
            return this;
        }

        public Builder compId(String compId) {
            this.compId = compId;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Recruiter build() {
            return new Recruiter(this);
        }
    }

    private String recId;
    private String compId;
    private String firstName;
    private String lastName;
    private String email;

    private Recruiter(Builder builder) {
        this.recId = builder.recId;
        this.compId = builder.compId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email =  builder.email;
    }

    public Recruiter() {
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public void setRecId(String recId) {
        this.recId = recId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRecId() {
        return recId;
    }

    public String getCompId() {
        return compId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
