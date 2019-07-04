package com.hongdu.filescaozuo;

public enum FileSuffixType {
    TXT(".txt"),
    SQL(".sql");

    private String suffix;


    FileSuffixType(String suffix) {
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }


}
