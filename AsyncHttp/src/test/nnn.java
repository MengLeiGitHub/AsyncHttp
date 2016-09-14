package test;

public class nnn {

	void test() throws Exception
    {
          try
          {
            int a = 1/0;
          }catch(Exception e){
            throw e;
          }
          finally
          {
            System.out.println("finally");
          }
    }
     
    public static void main(String[] args)
    {
        try
        {
            new nnn().test();
        }
        catch (Exception e)
        {
            System.out.println("Do you see me ?");
        } 
    }

}
