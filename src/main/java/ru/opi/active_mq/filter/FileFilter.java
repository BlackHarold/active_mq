package ru.opi.active_mq.filter;

import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.file.GenericFileFilter;

public class FileFilter<T> implements GenericFileFilter<T> {
    @Override
    public boolean accept(GenericFile<T> file) {
        return !file.getFileName().endsWith("csv");
    }
}
