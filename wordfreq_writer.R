# Author: lily-tian
# Date: 20 Aug 2016
# Word frequency writer

# reads in the raw data
words <- read.delim("data/all_words.csv", header = FALSE)
vars <- cbind("word", "year", "freq", "freqs")
colnames(words) <- vars

# make cap-insensitive and aggregates across all time periods
words$word <- toupper(words$word)
words$freq <- words$freq / 1000
words$freqs <- words$freqs / 1000
words.agg <- aggregate(cbind(freq, freqs) ~ word, data = words, FUN = sum)
words.agg$rank <- rank(max(words.agg$freq) - words.agg$freq, ties.method = "max")

# writes out table of word freqencies (in thousands)
write.table(words.agg, "wordfreq.txt", row.names = FALSE, col.names = FALSE)
