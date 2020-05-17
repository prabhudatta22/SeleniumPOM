package com.testCase.POJO;

public class Tax_info
{
    private String tax_id;

    private String type;

    public String getTax_id ()
    {
        return tax_id;
    }

    public void setTax_id (String tax_id)
    {
        this.tax_id = tax_id;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [tax_id = "+tax_id+", type = "+type+"]";
    }
}