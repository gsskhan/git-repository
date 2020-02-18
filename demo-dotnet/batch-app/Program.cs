using System;
using Validation;

namespace batch_app
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Batch started ...");
            var validation = new App();
            validation.checkStartUp();
        }
    }
}
