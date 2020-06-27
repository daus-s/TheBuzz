package com.buzz.model;

/*Email class
 *
 * Fields
 *  String: to, from, and contentName
 *      to: Email Address that is supplied by the user
 *      from: Email address that is defined in env
 *      contentName: path that is used to find the correct
 *                   resource that is used in the email
 *
 * Usage: this is created when an email request is made and isgiven its content based on the context in which it is called
 *        it is then passed as an argument to the email method, from where it will be sent
* */
public class Email
{
    private String to;
    private String from;
    private String contentName;

    //getters, setters for Email Object

    //to (the Address)
    public String getTo()
    {
        return to;
    }
    public void setTo(String to)
    {
        this.to = to;
    }

    //from address, i may delete later as this is always the company email address.
    public String getFrom()
    {
        return from;
    }
    public void setFrom(String from)
    {
        this.from = from;
    }

    //path name to the template used for the email(generalized)
    public String getContentName()
    {
        return contentName;
    }
    public void setContentName(String contentName)
    {
        this.contentName = contentName;
    }


    /*Constructors, 2 exist
    * one with only the to address and the other
    * with the to address as well as the file content
    * to be sent in the email. in generate message, a UUID
    * class will be leveraged in order to, int for message
    * is always required
    * */
    public Email(String to, String path)
    {
        this.to=to;
        this.contentName = path;
    }



}
