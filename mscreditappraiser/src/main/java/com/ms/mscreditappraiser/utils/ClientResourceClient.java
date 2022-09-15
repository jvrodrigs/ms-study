package com.ms.mscreditappraiser.utils;

import com.ms.mscreditappraiser.model.Dto.DataClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "msclients", path = "/clients")
public interface ClientResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<DataClient> getQueryCpf(@RequestParam("cpf") String cpf);

}
