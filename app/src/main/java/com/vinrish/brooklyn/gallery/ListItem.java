package com.vinrish.brooklyn.gallery;

public class ListItem {

        private String head;
        private String desc;
        private String imageUrl;
        private String email;
        private String image_id;

        public ListItem(String head, String desc, String imageUrl, String email) {
            this.head = head;
            this.desc = desc;
            this.imageUrl = imageUrl;
            this.email = email;
            this.image_id = image_id;
        }

        public String getHead() {
            return head;
        }

        public String getDesc() {
            return desc;
        }

        public String getImageUrl(){
            return imageUrl;
        }

        public String getEmail() { return email; }

        public String getImageId() { return image_id; }

}
