.NET core Apps - For demo
===========================


Console type app: batch-app
===========================

    $ mkdir batch-app
    $ cd batch-app
    $ dotnet new console

        Welcome to .NET Core 3.1!
        ---------------------
        SDK Version: 3.1.101

        Telemetry
        ---------
        The .NET Core tools collect usage data in order to help us improve your experience. The data is anonymous. It is collected by Microsoft and shared with the community. You can opt-out of telemetry by setting the DOTNET_CLI_TELEMETRY_OPTOUT environment variable to '1' or 'true' using your favorite shell.

        Read more about .NET Core CLI Tools telemetry: https://aka.ms/dotnet-cli-telemetry

        ----------------
        Explore documentation: https://aka.ms/dotnet-docs
        Report issues and find source on GitHub: https://github.com/dotnet/core
        Find out what's new: https://aka.ms/dotnet-whats-new
        Learn about the installed HTTPS developer cert: https://aka.ms/aspnet-core-https
        Use 'dotnet --help' to see available commands or visit: https://aka.ms/dotnet-cli-docs
        Write your first app: https://aka.ms/first-net-core-app
        --------------------------------------------------------------------------------------
        Getting ready...
        The template "Console Application" was created successfully.

        Processing post-creation actions...
        Running 'dotnet restore' on /media/gsskhan/WORK/git-repository-local/git-repository/demo-dotnet/batch-app/batch-app.csproj...
        Restore completed in 575.7 ms for /media/gsskhan/WORK/git-repository-local/git-repository/demo-dotnet/batch-app/batch-app.csproj.

        Restore succeeded.

    $ dotnet build
        Microsoft (R) Build Engine version 16.4.0+e901037fe for .NET Core
        Copyright (C) Microsoft Corporation. All rights reserved.

        Restore completed in 56.02 ms for /media/gsskhan/WORK/git-repository-local/git-repository/demo-dotnet/batch-app/batch-app.csproj.
        batch-app -> /media/gsskhan/WORK/git-repository-local/git-repository/demo-dotnet/batch-app/bin/Debug/netcoreapp3.1/batch-app.dll

        Build succeeded.
            0 Warning(s)
            0 Error(s)

        Time Elapsed 00:00:04.91

    $ dotnet run
        Hello World!
    
    To add new nuget package to project
    -----------------------------------
    $ dotnet add package <package name>

        Example: $ dotnet add package Newtonsoft.Json

        After the command completes, open the .csproj file to see the added reference.

    