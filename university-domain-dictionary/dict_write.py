#!/usr/bin/env python

CONST_FILENAME = "university-domain.csv"

def find(uni):
    f = open(CONST_FILENAME, "r")
    string = f.read()
    i = string.find(uni)
    domain = ""
    sdo = False #start domain
    while string[i]!=']':
        if string[i]==':':
            sdo=True
        if sdo:
            domain += str(string[i])
        i += 1
    #tuple (exists, uni, domain) -> types: (bool, str, str)
    t = (i!=-1, uni, domain)
    return t

def remove(uni, domain):
    f = open(CONST_FILENAME, "r")
    str = f.read()
    str = str.replace(concatonate(uni, domain) + ",", "")
    print(str)
    f.close()
    f = open(CONST_FILENAME, "w")
    f.write(str)
    f.close()

def append(uni, domain):
    write_to_file(concatonate(uni, domain))

def illegal_character(str):
    return ("[" in str or ":" in str or "]" in str)


def concatonate(name, defi):
    #for a specific format in the university-domain.csv
    return "[" + name + ":" + defi + "]"

def write_to_file(entry):
    f = open(CONST_FILENAME, "a")
    f.write(entry)
    f.write(",")
    f.close()


def main(args):
    resume = True
    while (resume==True):
        mode = input("enter mode(ap->append, rm->remove, se->find): ")
        #print("\n")
        uni = input("enter university name: ")
        #print("\n")
        domain = input("enter university domain: ")
        #print("\n")
        if illegal_character(uni) or illegal_character(domain):
            print("illegal character found. not performing actions to file")
        else:
            if mode=="se":
                print(find(uni))
            if mode=="ap":
                append(uni, domain)
            if mode=="rm":
                remove(uni, domain)
        resume = (input("continue? (y/n): ") == "y")




if __name__ == '__main__':
    import sys
    main(sys.argv)
