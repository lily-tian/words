# Words

This is a tool to determine the vocabulary level of a piece of writing. Currently a work in progress.

### Preliminary Data

The source of the data is from the Google Books Ngrams Viewer found at http://storage.googleapis.com/books/ngrams/books/datasetsv2.html

Because the datasets from Google were too large to download, pre-made .csv files using the Google 1-gram data was used. Both the .csv file and the Python script used to compile it are in the data folder. These materials were developed from the SRPC Summer Rockefeller Programming Class taught by Josh Hug, with all rights and credits belonging to him.

### Development

A R script was written to take the 1-gram data and aggregate all available years (1505-2008) for each word. Furthermore, every word was made case-insensitive. The rank variable was added, with 1 representing the most commonly found word.
