package com.buzz.model;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



    /**
     * Class Group, rarely instantiated, but extended by University/College, Business(Restaurants,...) and RSOs/clubs
     * The relationship between an Account, and a Group is "has-some"  i.e. the Group has some Accounts following
     * Groups can create posts that go to the followers
     */
    public class Business extends User implements RowDDB, Group
    {
        /**
         * The display name is the name that appears with the post and is linked to the
         * account. This does not need to be unique, however it often should, the key is not the email,
         * rather the id field generated on this field.
         */
        private String displayName = "group"; //defaults


        /**
         * The follower Arraylist(AL) contains all accounts that follow the account.
         * The number of followers is accessible through the arraylist, but is not important
         * as of v0.1. This is the list that will receive Posts from the group. When
         * following or un-following, the code must ensure the action is completed on both sides
         * with preference going to the Group if there is a discrepancy.
         */
        private ArrayList<String> followers = new ArrayList<String>();
        /**
         * List of all post that have not been deleted or expired based on time-to-live(later version)
         * Represented on the Groups page and displayed there. The caption and image are the only important
         * parts when being represented on the groups page.
         * */
        private ArrayList<String> posts = new ArrayList<String>();

        /**
         * Website that is displayed on the profile of the group. Non-unique requirement, however, it often
         * should be.
         */
        private String website = "thebuzz.com";

        /**
         * Logo image location,
         * This is the address of the logo in the S3 that is used as the universities "profile picture."
         * See mock rendering of app design to see image
         */
        private String logoIML = "default.png";

        /**
         * Physical address of the business, displayed on their page
         */
        private String address;


        /**
         *
         */
        private String phoneNumber;

        /**
         *
         */
        private ArrayList<String> universities = new ArrayList<String>();


        //follower & post accessors
        public ArrayList<String> getFollowers()
        {
            return followers;
        }
        public void setFollowers(ArrayList<String> l)
        {
            this.followers = l;
        }


        //post accessors and mutators
        public ArrayList<String> getPosts()
        {
            return posts;
        }
        public void setPosts(ArrayList<String> p)
        {
            this.posts = p;
        }


        //email accessors and mutators
        public String getEmail()
        {
            return email;
        }
        public void setEmail(String email)
        {
            this.email = email;
        }

        //username accessors and mutators
        public String getDisplayName()
        {
            return displayName;
        }
        public void setDisplayName(String displayName)
        {
            this.displayName = displayName;
        }

        //website accessors and mutators
        public String getWebsite()
        {
            return website;
        }
        public void setWebsite(String website)
        {
            this.website = website;
        }


        public String getLogoIML() {
            return logoIML;
        }
        public void setLogoIML(String logoIML) {
            this.logoIML = logoIML;
        }

        //physical address accessors and mutators
        public String getAddress()
        {
            return address;
        }
        public void setAddress(String address)
        {
            this.address = address;
        }


        //utility methods

        /**
         * @param a
         * @return returns whether the operation was successful or not(true-success, false-fail)
         * The method checks to see if the account is already following and then adds to the list.
         * Once an account follows, it will add to the accounts following.
         */
        public boolean addFollower(Account a)
        {
            return followers.add(a.getEmail());
        }

        /**
         * @param r
         * @return whether the follower was able to be removed
         * The method removes the account from the followers AL and then returns if this was successful.
         */
        public boolean removeFollower(Account r)
        {
            return followers.remove(r.getEmail());
        }

        /**
         * @param id
         * @return whether the post was found and removed
         * The post is removed from the account and the data of the post will not be
         * deleted from the s3. only mods have this power. NUKE BUTTON
         */
        public boolean deletePost(String id)
        {
            return posts.remove(id);
        }

        /**
         * @param p
         * @return whether the post was able to be created
         * This method publishes a post and sends out to followers. The method allows
         * for duplicate posting of the same post, this may change as it is possible to
         * take advantage of this.
         */
        public boolean createPost(Post p)
        {

            return posts.add(p.getId());

        }

        @Override
        public boolean deletePost(Post p)
        {
            return posts.remove(p.getId());
        }

        public Business(String displayName, String email, String website, String address, String hashedPwd1, String hashedPwd2, String phoneNumber, String university, char type, String id, String logoIML)
        {
            this.displayName = displayName;
            this.email = email;
            this.website = website;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.universities = new ArrayList<String>();
            this.universities.add(university);
            this.type = type;
            this.logoIML = logoIML;

        }

        public Business(String displayName, String email, String website, String address, String hashedPwd1, String hashedPwd2, String phoneNumber, String university, char type, String id)
        {
            this.displayName = displayName;
            this.email = email;
            this.website = website;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.universities = new ArrayList<String>();
            this.universities.add(university);
            this.type = type;

        }

        public Business(String displayName, String email, String website)
        {
            this.displayName = displayName;
            this.email = email;
            this.website = website;
        }


        public Business(String displayName, String email)
        {
            this.displayName = displayName;
            this.email = email;
        }

        public Business(String email)
        {
            this.email = email;
        }


        @Override
        public String getTableName()
        {
            return "business-table";
        }

        @Override
        public void loadModel(Map<String, AttributeValue> map)
        {
            this.type = 'b';
            this.email = map.get("email").getS();
            this.displayName = map.get("displayName").getS();
            this.followers = (ArrayList<String>) map.get("followers").getSS();
            ArrayList<String> names = (ArrayList<String>) map.get("posts").getSS();
            this.website = map.get("website").getS();
            this.logoIML = map.get("logoIML").getS();
            this.pwd = map.get("pwd").getS();
            this.universities = (ArrayList<String>) map.get("universities").getSS();
            this.phoneNumber = map.get("phoneNumber").getS();
            this.address = map.get("address").getS();

        }

        @Override
        public Map<String, AttributeValue> getModelAttributes()
        {
            Map<String, AttributeValue> itemValues = new HashMap<String, AttributeValue>();

            itemValues.put("email", new AttributeValue(this.email));
            itemValues.put("displayName", new AttributeValue(this.displayName));

            if (followers.size() > 0)
            {
                itemValues.put("followers", new AttributeValue(this.followers));
            }
            if (posts.size() > 0)
            {
                itemValues.put("posts", new AttributeValue());
            }
            if (universities.size() > 0)
            {
                itemValues.put("universities", new AttributeValue(this.universities));
            }
            itemValues.put("website", new AttributeValue(this.website));
            itemValues.put("logoIML", new AttributeValue(this.logoIML));
            itemValues.put("pwd", new AttributeValue(this.pwd));
            itemValues.put("phoneNumber", new AttributeValue(this.phoneNumber));
            itemValues.put("address", new AttributeValue(this.address));


            return itemValues;
        }

        @Override
        public String getKey()
        {
            return "email";
        }

        @Override
        public String getKeyValue()
        {
            return email;
        }


        public String getPhoneNumber()
        {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber)
        {
            this.phoneNumber = phoneNumber;
        }

        public ArrayList<String> getUniversities()
        {
            return universities;
        }

        public void setUniversities(ArrayList<String> universities)
        {
            this.universities = universities;
        }
    }
