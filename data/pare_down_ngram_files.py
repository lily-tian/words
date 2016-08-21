import csv
import gzip
import re

ofile = open("output.csv", "wb")
csv_writer = csv.writer(ofile, delimiter='\t')

def write_rows(rows, csv_writer):
    for row in rows:
        csv_writer.writerow(row)


files = ['a.gz', 'b.gz', 'c.gz', 'd.gz', 'e.gz', 'f.gz', 'g.gz', 'h.gz', 'i.gz', 'j.gz', 'k.gz', 'l.gz', 'm.gz', 'n.gz', 'o.gz', 'p.gz', 'q.gz', 'r.gz', 's.gz', 't.gz', 'u.gz', 'v.gz', 'w.gz', 'x.gz', 'y.gz', 'z.gz']

    
for filename in files:
    with gzip.open(filename, 'rb') as f:
        csv_reader = csv.reader(f, delimiter='\t')
        watch_word = ""
        max_seen = 0
        rows = []
        for row in csv_reader:
    
    
            current_word = row[0]
            if (current_word != watch_word):
                is_only_letters = (re.match("^[A-Za-z]*$", watch_word) != None)
                
                if (max_seen > 100000) and is_only_letters:
                    write_rows(rows, csv_writer)
                rows = []
                max_seen = 0
                watch_word = current_word
    
            rows.append(row)
            max_seen = max(max_seen, int(row[2]))
            
            
ofile.close()