package test;

import org.apache.solr.client.solrj.beans.Field;

public class ProjectBean {

    @Field
    private String name;

    @Field
    private String description;

    @Field
    private String manager;

    @Field
    private int ranking;

    private ProjectBean(String name, String description, String manager, int ranking) {
        this.name = name;
        this.description = description;
        this.manager = manager;
        this.ranking = ranking;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }


    public static ProjectBeanBuilder builder() {
        return new ProjectBeanBuilder();
    }


    public static class ProjectBeanBuilder {
        private String name;
        private String description;
        private String manager;
        private int ranking;

        public ProjectBeanBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProjectBeanBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProjectBeanBuilder manager(String manager) {
            this.manager = manager;
            return this;
        }

        public ProjectBeanBuilder ranking(int ranking) {
            this.ranking = ranking;
            return this;
        }

        public ProjectBean build() {
            return new ProjectBean(name, description, manager, ranking);
        }
    }


}
