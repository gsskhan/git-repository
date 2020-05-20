using System;
using Demo.Batch.App.Utilities;
using Demo.Batch.App.ValueObjects;
using Newtonsoft.Json;

namespace Demo.Batch.App
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
