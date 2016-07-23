package com.gov.iitnvli.datamodel;

/**
 * Created by Murtuza on 7/23/16.
 */
public class ResponseState {


    /**
     * success : true
     * result : [{"resource":{"entity_id":"15","node_title":"Test","language":"","rating":"20","source":"","type":"Book","tags":"","short_url":"","image_url":"http://dev-nvli.iitb.ac.in/sites/default/files/2016-07/Desert.jpg"},"metadata":{"id":"iitb-ds-100-1127","recordtype":"dspace","spelling":["iitb-ds-100-11272013-11-14T04:38:15ZTrie partitioning in distributed PC based routersATHAIDE, NOELKHAN, AZEEMMANJUNATH, DSAHOO, ANIRUDHAdistributed processingsoftware architecturetelecommunication network routingcommunication systemsRecent research in PC based routers has proposed a distributed architecture. Such an architecture poses several challenges in the areas of scalability, robustness, efficiency of routing, latency and other issues.We examine the issue of decrease in throughput due to large routing tables in this architecture and propose partitioning as a solution. Our contribution is twofold: defining the concept of load for a node in a forwarding table trie and to show by simulation experiments the effectiveness of partitioning and its application to a distributed router.IEEE2010-09-17T09:50:03Z2011-11-28T09:08:36Z2011-12-15T09:09:50Z2010-09-17T09:50:03Z2011-11-28T09:08:36Z2011-12-15T09:09:50Z2009ArticleProceedings of the First International Conference on Communication Systems and Networks, Bangalore, India, 5-10 January 2009, 1-10978-1-4244-2912-710.1109/COMSNETS.2009.4808884http://hdl.handle.net/10054/1733http://dspace.library.iitb.ac.in/xmlui/handle/10054/1733http://hdl.handle.net/100/1127en"],"institution":["Library"],"collection":["DSpace"],"language":["English"],"topic":["distributed processing","software architecture","telecommunication network routing","communication systems"],"spellingShingle":["distributed processing","software architecture","telecommunication network routing","communication systems","ATHAIDE, NOEL","Trie partitioning in distributed PC based routers"],"description":"Recent research in PC based routers has proposed a distributed architecture. Such an architecture poses several challenges in the areas of scalability, robustness, efficiency of routing, latency and other issues.We examine the issue of decrease in throughput due to large routing tables in this architecture and propose partitioning as a solution. Our contribution is twofold: defining the concept of load for a node in a forwarding table trie and to show by simulation experiments the effectiveness of partitioning and its application to a distributed router.","format":["Article"],"author":["ATHAIDE, NOEL"],"author2":["KHAN, AZEEM","MANJUNATH, D","SAHOO, ANIRUDHA"],"title":"Trie partitioning in distributed PC based routers","title_short":"Trie partitioning in distributed PC based routers","title_full":"Trie partitioning in distributed PC based routers","title_fullStr":"Trie partitioning in distributed PC based routers","title_full_unstemmed":"Trie partitioning in distributed PC based routers","title_sort":"trie partitioning in distributed pc based routers","publisher":["IEEE"],"publishDate":["2010"],"url":["http://hdl.handle.net/10054/1733","http://dspace.library.iitb.ac.in/xmlui/handle/10054/1733","http://hdl.handle.net/100/1127"],"_version_":1538862003492225024,"score":1}}]
     */

    private boolean success;
    /**
     * message : Node data not found.
     */

    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
