const char* CONST_FILENAME = "university-domain.csv";

char* format(const char* uni, const char* domain)
{
    return "[" + uni + :"" + domain + "]";
}

int append(const char* uni, const char* domain)
{
    char* entry = format(uni, domain);
    //open to write
    int open = open(CONST_FILENAME, O_WRONLY | O_APPEND);
    if (open==-1)
    {
        printf("error opening src, (%s)... %d\n", CONST_FILENAME, errno);
        return -1;
    }
    //write
    write(open, entry, strlen(entry));
    //close
    close(open);
    //clear memory
    free(entry);
    return 0;
}
//THIS NEEDS WORK STILL
int remove(const char* uni)
{
    char * red = malloc(1024); //play on read sounding like red in my head helps separate the meanings
    int open = open(CONST_FILENAME, O_RDWR);
    int bytes_read = read(open,red,1024);

    while (bytes_read > 0)
    {
        write(open_des, buf, bytes_read);
        bytes_read = read(open_src, buf, 1024);
    }

    for (size_t i = 0; i < bytes_read - strlen(uni); ++i)
    {
        if ()
    }

}

int main(int argc, char ** argv)
{
    if (argc!=4||argc!=3)
        exit(1);
    if (argv[1]=="rm")
    {
        remove(argv[2]);
    }
    if (argv[1]=="ap")
    {
        append(argv[2], argv[2]);
    }
}
