package com.wwx.teamall.controller;


import com.wwx.teamall.entity.DTO.GetApplyListDTO;
import com.wwx.teamall.entity.DTO.RefuseApplyDTO;
import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.TStoreApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wwx
 * @since 2023-03-20
 */
@RestController
@RequestMapping("/store/apply")
public class TStoreApplyController {

    @Autowired
    private TStoreApplyService storeApplyService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result getApplyList(@RequestBody GetApplyListDTO dto) {
       return storeApplyService.getList(dto);
    }

    @RequestMapping(value = "/agree", method = RequestMethod.POST)
    public Result agreeApply(@RequestParam("id") Integer id) {
        return storeApplyService.agreeApply(id);
    }

    @RequestMapping(value = "/refuse", method = RequestMethod.POST)
    public Result refuseApply(@RequestBody RefuseApplyDTO refuseApplyDTO) {
        return storeApplyService.refuseApply(refuseApplyDTO);
    }
}

