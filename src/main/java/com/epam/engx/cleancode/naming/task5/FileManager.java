package com.epam.engx.cleancode.naming.task5;

import com.epam.engx.cleancode.naming.task5.predicates.PredicateFileExtension;
import com.epam.engx.cleancode.naming.task5.thirdpartyjar.InvalidDirectoryException;
import com.epam.engx.cleancode.naming.task5.thirdpartyjar.InvalidFileTypeException;
import com.epam.engx.cleancode.naming.task5.thirdpartyjar.PropertyUtil;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public final class FileManager {

    private static final String[] VALID_IMAGE_TYPES = {"jpg", "png"};
    private static final String[] VALID_DOCUMENT_TYPES = {"pdf", "doc"};

    private String basePath = PropertyUtil.loadProperty("basePath");

    public File retrieveFile(String fileName) {
        validateFileType(fileName);
        final String dirPath = basePath + File.separator;
        return Paths.get(dirPath, fileName).toFile();
    }

    public List<String> listAllImages() {
        return getFiles(basePath, VALID_IMAGE_TYPES);
    }

    public List<String> listAllDocumentFiles() {
        return getFiles(basePath, VALID_DOCUMENT_TYPES);
    }

    private void validateFileType(String fileName) {
        if (isInvalidFileType(fileName)) {
            throw new InvalidFileTypeException("File type not Supported: " + fileName);
        }
    }

    private boolean isInvalidFileType(String fileName) {
        return isInvalidImage(fileName) && isInvalidDocument(fileName);
    }

    private boolean isInvalidImage(String fileName) {
        PredicateFileExtension imageExtensionsPredicate = new PredicateFileExtension(VALID_IMAGE_TYPES);
        return !imageExtensionsPredicate.test(fileName);
    }

    private boolean isInvalidDocument(String fileName) {
        PredicateFileExtension documentExtensionsPredicate = new PredicateFileExtension(VALID_DOCUMENT_TYPES);
        return !documentExtensionsPredicate.test(fileName);
    }

    private List<String> getFiles(String directoryPath, String[] allowedExtensions) {
        final PredicateFileExtension predicateFileExtension = new PredicateFileExtension(allowedExtensions);
        return Arrays.asList(directory(directoryPath).list(getFilenameFilterByPredicate(predicateFileExtension)));
    }

    private FilenameFilter getFilenameFilterByPredicate(final PredicateFileExtension fileExtension) {
        return new FilenameFilter() {
            @Override
            public boolean accept(File dir, String str) {
                return fileExtension.test(str);
            }
        };
    }

    private File directory(String directoryPath) {
        File directory = new File(directoryPath);
        validateDirectory(directory);
        return directory;
    }

    private void validateDirectory(File directoryInstance) {
        if (isNotDirectory(directoryInstance)) {
            throw new InvalidDirectoryException("Invalid directory found: " + directoryInstance.getAbsolutePath());
        }
    }

    private boolean isNotDirectory(File dir) {
        return !dir.isDirectory();
    }

}