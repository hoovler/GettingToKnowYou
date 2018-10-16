namespace TestProjectController
{
    #pragma warning disable // Disable all warnings

    public partial class Anonymous2 : System.ComponentModel.INotifyPropertyChanged
    {
        private string _id;
        private string _type;
        private string _slug;
        private string _jobTitle;
        private string _firstName;
        private string _lastName;
        private Headshot _headshot = new Headshot();
        private System.Collections.Generic.List<Anonymous3> _socialLinks = new System.Collections.Generic.List<Anonymous3>();
        private string _bio;
    
        [Newtonsoft.Json.JsonProperty("id", Required = Newtonsoft.Json.Required.Always)]
        [System.ComponentModel.DataAnnotations.Required(AllowEmptyStrings = true)]
        public string Id
        {
            get { return _id; }
            set 
            {
                if (_id != value)
                {
                    _id = value; 
                    RaisePropertyChanged();
                }
            }
        }
    
        [Newtonsoft.Json.JsonProperty("type", Required = Newtonsoft.Json.Required.Always)]
        [System.ComponentModel.DataAnnotations.Required(AllowEmptyStrings = true)]
        public string Type
        {
            get { return _type; }
            set 
            {
                if (_type != value)
                {
                    _type = value; 
                    RaisePropertyChanged();
                }
            }
        }
    
        [Newtonsoft.Json.JsonProperty("slug", Required = Newtonsoft.Json.Required.Always)]
        [System.ComponentModel.DataAnnotations.Required(AllowEmptyStrings = true)]
        public string Slug
        {
            get { return _slug; }
            set 
            {
                if (_slug != value)
                {
                    _slug = value; 
                    RaisePropertyChanged();
                }
            }
        }
    
        [Newtonsoft.Json.JsonProperty("jobTitle", Required = Newtonsoft.Json.Required.Default, NullValueHandling = Newtonsoft.Json.NullValueHandling.Ignore)]
        public string JobTitle
        {
            get { return _jobTitle; }
            set 
            {
                if (_jobTitle != value)
                {
                    _jobTitle = value; 
                    RaisePropertyChanged();
                }
            }
        }
    
        [Newtonsoft.Json.JsonProperty("firstName", Required = Newtonsoft.Json.Required.Always)]
        [System.ComponentModel.DataAnnotations.Required(AllowEmptyStrings = true)]
        public string FirstName
        {
            get { return _firstName; }
            set 
            {
                if (_firstName != value)
                {
                    _firstName = value; 
                    RaisePropertyChanged();
                }
            }
        }
    
        [Newtonsoft.Json.JsonProperty("lastName", Required = Newtonsoft.Json.Required.Always)]
        [System.ComponentModel.DataAnnotations.Required(AllowEmptyStrings = true)]
        public string LastName
        {
            get { return _lastName; }
            set 
            {
                if (_lastName != value)
                {
                    _lastName = value; 
                    RaisePropertyChanged();
                }
            }
        }
    
        [Newtonsoft.Json.JsonProperty("headshot", Required = Newtonsoft.Json.Required.Always)]
        [System.ComponentModel.DataAnnotations.Required]
        public Headshot Headshot
        {
            get { return _headshot; }
            set 
            {
                if (_headshot != value)
                {
                    _headshot = value; 
                    RaisePropertyChanged();
                }
            }
        }
    
        [Newtonsoft.Json.JsonProperty("socialLinks", Required = Newtonsoft.Json.Required.Always)]
        [System.ComponentModel.DataAnnotations.Required]
        public System.Collections.Generic.List<Anonymous3> SocialLinks
        {
            get { return _socialLinks; }
            set 
            {
                if (_socialLinks != value)
                {
                    _socialLinks = value; 
                    RaisePropertyChanged();
                }
            }
        }
    
        [Newtonsoft.Json.JsonProperty("bio", Required = Newtonsoft.Json.Required.Default, NullValueHandling = Newtonsoft.Json.NullValueHandling.Ignore)]
        public string Bio
        {
            get { return _bio; }
            set 
            {
                if (_bio != value)
                {
                    _bio = value; 
                    RaisePropertyChanged();
                }
            }
        }
    
        public string ToJson() 
        {
            return Newtonsoft.Json.JsonConvert.SerializeObject(this);
        }
        
        public static Anonymous2 FromJson(string data)
        {
            return Newtonsoft.Json.JsonConvert.DeserializeObject<Anonymous2>(data);
        }
    
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected virtual void RaisePropertyChanged([System.Runtime.CompilerServices.CallerMemberName] string propertyName = null)
        {
            var handler = PropertyChanged;
            if (handler != null) 
                handler(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
        }
    
    }
    
    [System.CodeDom.Compiler.GeneratedCode("NJsonSchema", "9.11.0.0 (Newtonsoft.Json v9.0.0.0)")]
    public partial class Anonymous : System.Collections.ObjectModel.ObservableCollection<Anonymous2>, System.ComponentModel.INotifyPropertyChanged
    {
    
        public string ToJson() 
        {
            return Newtonsoft.Json.JsonConvert.SerializeObject(this);
        }
        
        public static Anonymous FromJson(string data)
        {
            return Newtonsoft.Json.JsonConvert.DeserializeObject<Anonymous>(data);
        }
    
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected virtual void RaisePropertyChanged([System.Runtime.CompilerServices.CallerMemberName] string propertyName = null)
        {
            var handler = PropertyChanged;
            if (handler != null) 
                handler(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
        }
    
    }
    
    [System.CodeDom.Compiler.GeneratedCode("NJsonSchema", "9.11.0.0 (Newtonsoft.Json v9.0.0.0)")]
    public partial class Headshot : System.ComponentModel.INotifyPropertyChanged
    {
        private string _type;
        private string _mimeType;
        private string _id;
        private string _url;
        private string _alt;
        private int? _height;
        private int? _width;
    
        [Newtonsoft.Json.JsonProperty("type", Required = Newtonsoft.Json.Required.Always)]
        [System.ComponentModel.DataAnnotations.Required(AllowEmptyStrings = true)]
        public string Type
        {
            get { return _type; }
            set 
            {
                if (_type != value)
                {
                    _type = value; 
                    RaisePropertyChanged();
                }
            }
        }
    
        [Newtonsoft.Json.JsonProperty("mimeType", Required = Newtonsoft.Json.Required.Default, NullValueHandling = Newtonsoft.Json.NullValueHandling.Ignore)]
        public string MimeType
        {
            get { return _mimeType; }
            set 
            {
                if (_mimeType != value)
                {
                    _mimeType = value; 
                    RaisePropertyChanged();
                }
            }
        }
    
        [Newtonsoft.Json.JsonProperty("id", Required = Newtonsoft.Json.Required.Always)]
        [System.ComponentModel.DataAnnotations.Required(AllowEmptyStrings = true)]
        public string Id
        {
            get { return _id; }
            set 
            {
                if (_id != value)
                {
                    _id = value; 
                    RaisePropertyChanged();
                }
            }
        }
    
        [Newtonsoft.Json.JsonProperty("url", Required = Newtonsoft.Json.Required.Default, NullValueHandling = Newtonsoft.Json.NullValueHandling.Ignore)]
        public string Url
        {
            get { return _url; }
            set 
            {
                if (_url != value)
                {
                    _url = value; 
                    RaisePropertyChanged();
                }
            }
        }
    
        [Newtonsoft.Json.JsonProperty("alt", Required = Newtonsoft.Json.Required.Always)]
        [System.ComponentModel.DataAnnotations.Required(AllowEmptyStrings = true)]
        public string Alt
        {
            get { return _alt; }
            set 
            {
                if (_alt != value)
                {
                    _alt = value; 
                    RaisePropertyChanged();
                }
            }
        }
    
        [Newtonsoft.Json.JsonProperty("height", Required = Newtonsoft.Json.Required.Default, NullValueHandling = Newtonsoft.Json.NullValueHandling.Ignore)]
        public int? Height
        {
            get { return _height; }
            set 
            {
                if (_height != value)
                {
                    _height = value; 
                    RaisePropertyChanged();
                }
            }
        }
    
        [Newtonsoft.Json.JsonProperty("width", Required = Newtonsoft.Json.Required.Default, NullValueHandling = Newtonsoft.Json.NullValueHandling.Ignore)]
        public int? Width
        {
            get { return _width; }
            set 
            {
                if (_width != value)
                {
                    _width = value; 
                    RaisePropertyChanged();
                }
            }
        }
    
        public string ToJson() 
        {
            return Newtonsoft.Json.JsonConvert.SerializeObject(this);
        }
        
        public static Headshot FromJson(string data)
        {
            return Newtonsoft.Json.JsonConvert.DeserializeObject<Headshot>(data);
        }
    
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected virtual void RaisePropertyChanged([System.Runtime.CompilerServices.CallerMemberName] string propertyName = null)
        {
            var handler = PropertyChanged;
            if (handler != null) 
                handler(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
        }
    
    }
    
    [System.CodeDom.Compiler.GeneratedCode("NJsonSchema", "9.11.0.0 (Newtonsoft.Json v9.0.0.0)")]
    public partial class Anonymous3 : System.ComponentModel.INotifyPropertyChanged
    {
        private string _type;
        private string _callToAction;
        private string _url;
    
        [Newtonsoft.Json.JsonProperty("type", Required = Newtonsoft.Json.Required.Always)]
        [System.ComponentModel.DataAnnotations.Required(AllowEmptyStrings = true)]
        public string Type
        {
            get { return _type; }
            set 
            {
                if (_type != value)
                {
                    _type = value; 
                    RaisePropertyChanged();
                }
            }
        }
    
        [Newtonsoft.Json.JsonProperty("callToAction", Required = Newtonsoft.Json.Required.Always)]
        [System.ComponentModel.DataAnnotations.Required(AllowEmptyStrings = true)]
        public string CallToAction
        {
            get { return _callToAction; }
            set 
            {
                if (_callToAction != value)
                {
                    _callToAction = value; 
                    RaisePropertyChanged();
                }
            }
        }
    
        [Newtonsoft.Json.JsonProperty("url", Required = Newtonsoft.Json.Required.Always)]
        [System.ComponentModel.DataAnnotations.Required(AllowEmptyStrings = true)]
        public string Url
        {
            get { return _url; }
            set 
            {
                if (_url != value)
                {
                    _url = value; 
                    RaisePropertyChanged();
                }
            }
        }
    
        public string ToJson() 
        {
            return Newtonsoft.Json.JsonConvert.SerializeObject(this);
        }
        
        public static Anonymous3 FromJson(string data)
        {
            return Newtonsoft.Json.JsonConvert.DeserializeObject<Anonymous3>(data);
        }
    
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected virtual void RaisePropertyChanged([System.Runtime.CompilerServices.CallerMemberName] string propertyName = null)
        {
            var handler = PropertyChanged;
            if (handler != null) 
                handler(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
        }
    
    }

}