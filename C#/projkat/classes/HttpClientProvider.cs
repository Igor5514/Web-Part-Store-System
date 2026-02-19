using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace projkat.classes
{
    public static class HttpClientProvider
    {
        public static readonly HttpClient Client = new HttpClient
        {
            Timeout = TimeSpan.FromSeconds(30) 

        };
    }
}
