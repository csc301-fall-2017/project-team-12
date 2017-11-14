package com.stackd.stackd.db.entities;

/**
 * Created by user on 11/6/2017.
 */

public class Recruiter  {
    /* Builder to populate the fields of a Recruiter object */
    public static class Builder {
        private long recId;
        private long compId;
        private String firstName;
        private String lastName;
        private String email;

        public Builder redId(long recId) {
            this.recId = recId;
            return this;
        }

        public Builder compId(long compId) {
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

    private long recId;
    private long compId;
    private String firstName;
    private String lastName;
    private String email;

    public Recruiter(Builder builder) {
        this.recId = builder.recId;
        this.compId = builder.compId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email =  builder.email;
    }

    public long getRecId() {
        return recId;
    }

    public long getCompId() {
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
