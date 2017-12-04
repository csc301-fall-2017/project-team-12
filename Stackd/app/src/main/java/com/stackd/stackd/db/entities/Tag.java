package com.stackd.stackd.db.entities;

public class Tag  {
    /* Builder to set the fields of the Tag */
    public static class Builder {
        private String id;
        private String name;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Tag build() {
            return new Tag(this);
        }
    }

    private String id;
    private String name;

    private Tag(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public Tag() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object tag) {
        return tag instanceof Tag && this.id.equals(((Tag)tag).id) && this.getName().equals(((Tag) tag).getName());
    }

    @Override public String toString() {
        return this.getName();
    }
}
