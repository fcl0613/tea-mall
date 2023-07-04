package com.wwx.teamall.controller;


import com.wwx.teamall.entity.TAddress;
import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.TAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wwx
 * @since 2023-03-29
 */
@RestController
@RequestMapping("/address")
public class TAddressController {
    @Autowired
    private TAddressService addressService;

    @PostMapping("/add")
    public Result addAddress(@RequestBody TAddress address) {
        return addressService.addAddress(address);
    }

    @PostMapping("/update")
    public Result updateAddress(@RequestBody TAddress address) {
        return addressService.updateAddress(address);
    }

    @GetMapping("/list")
    public Result getAddressList() {
        return addressService.getAddressList();
    }

    @PostMapping("/delete")
    public Result deleteAddress(@RequestParam("id") Integer id) {
        return addressService.deleteAddress(id);
    }

    @GetMapping("/detail")
    public Result getAddressDetail(@RequestParam("id") Integer id) {
        return addressService.getAddressDetail(id);
    }
}

