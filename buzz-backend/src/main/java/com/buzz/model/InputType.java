package com.buzz.model;


public class InputType
{
    private String string;

    public InputType(String string)
    {
        this.string = string;
    }

    public String getString()
    {
        return string;
    }

    public void setString(String string)
    {
        this.string = string;
    }

    public String toString()
    {
        return this.string;
    }

    public boolean equals(Object o)
    {
        if (o instanceof String)
        {
            return this.string.equals(o);
        }
        if (o instanceof InputType)
        {
            InputType a = (InputType) o;
            return this.string.equals(a.getString());
        }
        return false;
    }
}
