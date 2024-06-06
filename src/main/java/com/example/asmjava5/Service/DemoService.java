package com.example.asmjava5.Service;

import com.example.asmjava5.Entity.SanPham;
import com.example.asmjava5.Repository.SanPhamDemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemoService {

    @Autowired
    SanPhamDemoRepository repo;

    public List<SanPham> getAllSP(Integer pageNo, Integer pageSize, String sortBy, String sortOrder) {
        Pageable paging;
        if (sortOrder == "asc") {
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
        } else if (sortOrder == "desc") {
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        } else {
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        }

        Page<SanPham> pagedResult = repo.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<SanPham>();
        }
    }
}
