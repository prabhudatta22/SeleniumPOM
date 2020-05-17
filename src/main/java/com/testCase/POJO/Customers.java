package com.testCase.POJO;

public class Customers
{
    private Data[] data;

    private boolean has_more;

    private String object;

    private String url;

    public Data[] getData ()
    {
        return data;
    }

    public void setData (Data[] data)
    {
        this.data = data;
    }

    public boolean getHas_more ()
    {
        return has_more;
    }

    public void setHas_more (boolean has_more)
    {
        this.has_more = has_more;
    }

    public String getObject ()
    {
        return object;
    }

    public void setObject (String object)
    {
        this.object = object;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+", has_more = "+has_more+", object = "+object+", url = "+url+"]";
    }
}