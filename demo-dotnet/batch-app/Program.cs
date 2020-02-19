using System;
using AppUtilities;
using AppValueObjects;
using Newtonsoft.Json;

namespace batch_app
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Batch started ...");
            var validation = new Validation();
            validation.checkStartUp();

            Account account = new Account();
            account.Name = "John Doe";
            account.Email = "john.doe@gmail.com";
            account.DOB = new DateTime(1980, 2, 20, 0, 0, 0, DateTimeKind.Utc);
            String json = JsonConvert.SerializeObject(account, Formatting.Indented);
            Console.WriteLine("Account Json = " + json);
        }
    }
}
