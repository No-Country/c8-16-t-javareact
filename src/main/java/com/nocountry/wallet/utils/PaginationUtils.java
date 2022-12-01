package com.nocountry.wallet.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public class PaginationUtils {
    private final Integer numberOfPage;
    private final String path;
    private final Page<?> pageObject;

    public PaginationUtils(JpaRepository repository, Integer numberOfPage, Integer quantityOfResults, String path) {
        this.numberOfPage = numberOfPage;
        this.path = path;
        Pageable pageable = PageRequest.of(numberOfPage - 1, quantityOfResults);
        this.pageObject = repository.findAll(pageable);
    }

    public Page<?> getPage() {
        return pageObject;
    }

    public String getPrevious() {
        if (pageObject.hasPrevious()) {
            return String.format(path, numberOfPage - 1);
        }
        return null;
    }

    public String getNext() {
        if (pageObject.hasNext()) {
            return String.format(path, numberOfPage + 1);
        }
        return null;
    }
}
