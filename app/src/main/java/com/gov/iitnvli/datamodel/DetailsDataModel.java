package com.gov.iitnvli.datamodel;

import java.util.List;

/**
 * Created by Murtuza on 7/23/16.
 */
public class DetailsDataModel extends ResponseState {

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {

        private ResourceBean resource;

        private MetadataBean metadata;

        public ResourceBean getResource() {
            return resource;
        }

        public void setResource(ResourceBean resource) {
            this.resource = resource;
        }

        public MetadataBean getMetadata() {
            return metadata;
        }

        public void setMetadata(MetadataBean metadata) {
            this.metadata = metadata;
        }

        public static class ResourceBean {
            private String entity_id;
            private String node_title;
            private String language;
            private String rating;
            private String source;
            private String type;
            private String short_url;
            private String image_url;

            public String getEntity_id() {
                return entity_id;
            }

            public void setEntity_id(String entity_id) {
                this.entity_id = entity_id;
            }

            public String getNode_title() {
                return node_title;
            }

            public void setNode_title(String node_title) {
                this.node_title = node_title;
            }

            public String getLanguage() {
                return language;
            }

            public void setLanguage(String language) {
                this.language = language;
            }

            public String getRating() {
                return rating;
            }

            public void setRating(String rating) {
                this.rating = rating;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getShort_url() {
                return short_url;
            }

            public void setShort_url(String short_url) {
                this.short_url = short_url;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }
        }

        public static class MetadataBean {
            private String id;
            private String recordtype;
            private String description;
            private String title;
            private String title_short;
            private String title_full;
            private String title_fullStr;
            private String title_full_unstemmed;
            private String title_sort;
            private long _version_;
            private int score;
            private List<String> spelling;
            private List<String> institution;
            private List<String> collection;
            private List<String> language;
            private List<String> topic;
            private List<String> spellingShingle;
            private List<String> format;
            private List<String> author;
            private List<String> author2;
            private List<String> publisher;
            private List<String> publishDate;
            private List<String> url;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRecordtype() {
                return recordtype;
            }

            public void setRecordtype(String recordtype) {
                this.recordtype = recordtype;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTitle_short() {
                return title_short;
            }

            public void setTitle_short(String title_short) {
                this.title_short = title_short;
            }

            public String getTitle_full() {
                return title_full;
            }

            public void setTitle_full(String title_full) {
                this.title_full = title_full;
            }

            public String getTitle_fullStr() {
                return title_fullStr;
            }

            public void setTitle_fullStr(String title_fullStr) {
                this.title_fullStr = title_fullStr;
            }

            public String getTitle_full_unstemmed() {
                return title_full_unstemmed;
            }

            public void setTitle_full_unstemmed(String title_full_unstemmed) {
                this.title_full_unstemmed = title_full_unstemmed;
            }

            public String getTitle_sort() {
                return title_sort;
            }

            public void setTitle_sort(String title_sort) {
                this.title_sort = title_sort;
            }

            public long get_version_() {
                return _version_;
            }

            public void set_version_(long _version_) {
                this._version_ = _version_;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public List<String> getSpelling() {
                return spelling;
            }

            public void setSpelling(List<String> spelling) {
                this.spelling = spelling;
            }

            public List<String> getInstitution() {
                return institution;
            }

            public void setInstitution(List<String> institution) {
                this.institution = institution;
            }

            public List<String> getCollection() {
                return collection;
            }

            public void setCollection(List<String> collection) {
                this.collection = collection;
            }

            public List<String> getLanguage() {
                return language;
            }

            public void setLanguage(List<String> language) {
                this.language = language;
            }

            public List<String> getTopic() {
                return topic;
            }

            public void setTopic(List<String> topic) {
                this.topic = topic;
            }

            public List<String> getSpellingShingle() {
                return spellingShingle;
            }

            public void setSpellingShingle(List<String> spellingShingle) {
                this.spellingShingle = spellingShingle;
            }

            public List<String> getFormat() {
                return format;
            }

            public void setFormat(List<String> format) {
                this.format = format;
            }

            public List<String> getAuthor() {
                return author;
            }

            public void setAuthor(List<String> author) {
                this.author = author;
            }

            public List<String> getAuthor2() {
                return author2;
            }

            public void setAuthor2(List<String> author2) {
                this.author2 = author2;
            }

            public List<String> getPublisher() {
                return publisher;
            }

            public void setPublisher(List<String> publisher) {
                this.publisher = publisher;
            }

            public List<String> getPublishDate() {
                return publishDate;
            }

            public void setPublishDate(List<String> publishDate) {
                this.publishDate = publishDate;
            }

            public List<String> getUrl() {
                return url;
            }

            public void setUrl(List<String> url) {
                this.url = url;
            }
        }
    }
}
