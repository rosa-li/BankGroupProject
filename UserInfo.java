
public class UserInfo
{
    //Initializing private variables
    private String strName, strLastName, strPassword;  //Used to store the users first and last name, and passowrd to get into the account
    private long lngCardNumber;  //Long variable type to store the users card number
    private float fltMoney, fltWithdraws;  //Float variable type to store the amount of money they have and withdraws
    
    //Constuctor 
    UserInfo (String fn, String ln, String pass, long cardNum, float money, float withdraws)
    {
        this.strName = fn;
        this.strLastName = ln;
        this.strPassword = pass;
        this.lngCardNumber = cardNum;
        this.fltMoney = money;
        this.fltWithdraws = withdraws;
    }
    
    //Default Constuctor
    UserInfo ()
    {
        this.strName = null;
        this.strLastName = null;
        this.strPassword = null;
        this.lngCardNumber = 0;
        this.fltMoney = 0;
        this.fltWithdraws = 0;
    }
    
    //Set method for strName
    public void setName(String fn)
    {
        this.strName = fn;
    }
    
    //Set method for strLastName
    public void setLastName(String ln)
    {
        this.strLastName = ln;
    }
    
    //Set method for strPassword
    public void setPassword(String pass)
    {
        this.strPassword = pass;
    }
    
    //Set method for lngCardNumber
    public void setCardNum(long cn)
    {
        this.lngCardNumber = cn;
    }
    
    //Set method for fltMoney
    public void setMoney(float money)
    {
        this.fltMoney = money;
    }
    
    //Set method for fltWithdraws
    public void setWithdraws(float with)
    {
        this.fltWithdraws = with;
    }
    
    //Get method for Name
    public String getName()
    {
        return this.strName;
    }
    
    //Get method for LastName
    public String getLastName()
    {
        return this.strLastName;
    }
    
    //Get method for Password
    public String getPassword()
    {
        return this.strPassword;
    }
    
    //Get method for CardNumber
    public long getCardNum()
    {
        return this.lngCardNumber;
    }
    
    //Get method for Money
    public float getMoney()
    {
        return this.fltMoney;
    }
    
    //Get method for withdraws
    public float getWithdraws()
    {
        return this.fltWithdraws;
    }
}
