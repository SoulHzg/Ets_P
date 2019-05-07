package com.ets.producer.mapper;

import com.ets.common.bean.BookBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface BookMapper {
    public void addBook(BookBean bookBean);

    public List<BookBean> qryBookList();

    public BookBean qryBookById(int id);
}
