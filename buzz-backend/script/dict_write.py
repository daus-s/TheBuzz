#!/usr/bin/env python

CONST_FILENAME = "../src/main/resources/static/university-domain.js"

def find(uni):
    '''
    returns a tuple of (bool, string, string) that lists the attributes of the specified dictionary entry
    uses file open in read only mode and then subsequently processes through the string from ${CONST_FILENAME}
    to return the domain.
    '''
    f = open(CONST_FILENAME, "r")
    string = f.read()
    i = string.find(uni)
    if i==-1:
        return (False, uni, "")
    domain = ""
    sdo = False #start domain
    while string[i]!='\"' and not sdo:
        if sdo:
            domain += str(string[i])
        if string[i]==',':
            i+=10
            sdo=True
        i += 1
    #tuple (exists, uni, domain) -> types: (bool, str, str)
    t = (True, uni, domain)
    return t

def remove(uni, domain):
    '''
    not return value. removes the specified university/domain entry. requires open the file twice, slightly less efficeint than
    the other methods. removes following comma too from the .json file
    '''
    f = open(CONST_FILENAME, "r")
    str = f.read()
    entry = concatonate(uni, domain)
    entry = entry[1:len(entry)]
    print(entry)
    print(str.find(entry))
    str = str.replace(entry + ",", "")
    print(str)
    f.close()
    f = open(CONST_FILENAME, "w")
    f.write(str)
    f.close()

def append(uni, domain):
    '''
    writes the new entry in format \"[University Name,domain.edu]\"
    '''
    write_to_file(concatonate(uni, domain))

def illegal_character(str):
    '''
    checks if the str contains any illegal characters for this format. this ensures that no processing error can happen when
    the program examines it.
    '''
    return ("[" in str or ":" in str or "]" in str or "\"" in str or "," in str or "{" in str or "}" in str)


def concatonate(name, defi):
    '''
    returns a string in the specified format for json \",{\"name\":\"University of Washington\",\"domain\":\"uw.edu\"}\"
    '''
    #for a specific format in the university-domain.json
    return ",{\"name\":\"" + name + "\",\"domain\":\"" + defi + "\"}\n"

def write_to_file(entry):
    '''
    opens and appends a string object to the ${CONST_FILENAME} with a comma attached to match .csv convention
    '''
    f = open(CONST_FILENAME, "r")
    str = f.read()
    str = str[0:len(str)-3]
    f.close()
    f = open(CONST_FILENAME, "w")
    f.write(str)
    f.close()
    f = open(CONST_FILENAME, "a")
    f.write(entry)
    f.write("\n]}")
    f.close()


def main(args):
    '''
    program taking in university name and domain to add to ${CONST_FILENAME} for processing in the backend of the buzz
    program for student status checking (SSC), and registering groups.
    '''
    resume = True
    domain = ""
    uni = ""
    mode = ""
    while (resume==True):
        mode = input("enter mode(ap->append, rm->remove, se->find): ")
        uni = input("enter university name: ")
        if mode !="se":
            domain = input("enter university domain: ")
        if illegal_character(uni) or illegal_character(domain):
            print("illegal character found. not performing actions to file")
        else:
            if mode=="se":
                print(find(uni))
            if mode=="ap":
                if not find(uni)[0]:
                    append(uni, domain)
                else:
                    print("university already exists")
            if mode=="rm":
                remove(uni, domain)
        resume = (input("continue? (y/n): ") == "y")




if __name__ == '__main__':
    import sys
    main(sys.argv)
