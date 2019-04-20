package com.woniu.yoga.cf.dao;

import com.woniu.yoga.crowdfunding.dao.CrowdFundingMapper;
import com.woniu.yoga.crowdfunding.pojo.CrowdFunding;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrowdFundingMapperTest {

        @Autowired
        private CrowdFundingMapper crowdFundingMapper;
        String condition = "cf_sup_count";
        String type = "desc";

        @Test
        public void get(){
            CrowdFunding cf = new CrowdFunding();
            cf.setCfStatus(1);
            System.out.println(crowdFundingMapper.getCfs(cf, condition, type));
        }


}