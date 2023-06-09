package com.education.controller.nomenclature;

import com.education.entity.Nomenclature;
import com.education.service.nomenclature.NomenclatureService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import model.dto.NomenclatureDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import java.io.UnsupportedEncodingException;
import java.util.Collection;

import static com.education.mapper.NomenclatureMapper.NOMENCLATURE_MAPPER;

/**
 * RestController of edo-repository. 
 */
@AllArgsConstructor
@RestController
@Log4j2
@RequestMapping("/api/repository/nomenclature")
public class NomenclatureController {
    private NomenclatureService service;

    /**
     * Delete entity of Nomenclature by its id
     */
    @ApiOperation("Method delete Nomenclature by Id")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<NomenclatureDto> delete(@PathVariable Long id) {
        log.info("delete entity with id = {}", id);
        service.deleteById(id);
        log.info("Entity with id = {} was deleted", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Method searches for an entity of Nomenclature
     */
    @ApiOperation("Method searches for an entity of Nomenclature by its ID")
    @GetMapping(value = "/find/{id}")
    public ResponseEntity<NomenclatureDto> findById(@PathVariable Long id) {
        log.info("Serching entity with id = {}", id);
        NomenclatureDto nomenclatureDto = NOMENCLATURE_MAPPER.toDto(service.findById(id));
        log.info("Entity {} has been found",nomenclatureDto);
        return new ResponseEntity<>(nomenclatureDto, HttpStatus.OK);
    }

    @ApiOperation("Method find Nomenclature by Id")
    @GetMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<NomenclatureDto>> findByIndex(@RequestParam("index")  String index) throws UnsupportedEncodingException {
        log.info("Поиск NomenclatureDto по индексу: {}", index);
        var decodeParam  = UriUtils.decode(index, "UTF-8");
        log.info("index after decode {}", decodeParam);
        Collection<NomenclatureDto> nomenclatureDtos = NOMENCLATURE_MAPPER.toDto(service.findByIndex(decodeParam));
        log.info("Entity {} has been found", nomenclatureDtos);
        return new ResponseEntity<>(nomenclatureDtos, HttpStatus.OK);
    }

    /**
     * the Method fills in the field with the value and set date
     */
    @ApiOperation(value = "MOVE TO ARHCIVE (SET DATE)")
    @GetMapping("/move/{id}")
    public ResponseEntity<NomenclatureDto> move(@PathVariable Long id) {
        log.debug("Set field archived_date with actual datetime");
        service.moveToArchive(id);
        log.debug("Set field archived_date with actual datetime");
        NomenclatureDto nomenclatureDto = NOMENCLATURE_MAPPER.toDto(service.findById(id));
        return new ResponseEntity<>(nomenclatureDto, HttpStatus.OK);
    }

    /**
     * Method searches for an entity of Nomenclature that archiveDate fild is null
     */
    @ApiOperation(value = "Method finds nomenclature if it has not been archived")
    @GetMapping("/notArch/{id}")
    public ResponseEntity<NomenclatureDto> findByIdNotArchivedController(@PathVariable Long id) {
        log.info("Searching entity with empty archived_date field");
        NomenclatureDto nomenclatureDto = NOMENCLATURE_MAPPER.toDto(service.findByIdNotArchived(id).get());
        log.info("Archived object has been identified: {}", nomenclatureDto);
        return new ResponseEntity<>(nomenclatureDto, HttpStatus.OK);
    }

    /**
     * Method searches for set of entities of Nomenclature that archiveDate filds are null
     */
    @ApiOperation(value = "find list of Nomenclature`s entities if they have not been archived")
    @GetMapping(value = "/notArchList", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<NomenclatureDto>> findAllByIdNotArchivedController(@RequestParam("id") Collection<Long> listId) {
        log.info("Searching entities with empty archived_date fields");
        Collection<NomenclatureDto> nomenclatureDtoList = NOMENCLATURE_MAPPER.toDto(service.findAllByIdNotArchived(listId));
        log.info("Archived objects has been identified: {}", nomenclatureDtoList);
        return new ResponseEntity<>(nomenclatureDtoList, HttpStatus.OK);
    }

    /**
     * Method searches for set of entities of Nomenclature by their ids in Collection
     */
    @ApiOperation("find all entites of nomenclature which are in Collectionn<Long> of id")
    @GetMapping(value = "/all")
    public ResponseEntity<Collection<NomenclatureDto>> findAllByIdController(@RequestParam("id") Collection<Long> listId) {
        log.info("Searching entity with id list = {}", listId);
        Collection<NomenclatureDto> nomenclatureDtoList = NOMENCLATURE_MAPPER.toDto(service.findAllById(listId));
        log.info("List of entities has been identified: {}", nomenclatureDtoList);
        return new ResponseEntity<>(nomenclatureDtoList, HttpStatus.OK);
    }


    /**
     * Method saves new entity in DB by accepting jsom-body object
     */
    @ApiOperation("save new entity of Nomenclature by request with json-body")
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NomenclatureDto> saveDefaultEntity(@RequestBody NomenclatureDto nomenclatureDto) {
        log.info(" DTO object of Nomenclature ({}) will be saved ", nomenclatureDto);
        Nomenclature save = service.save(NOMENCLATURE_MAPPER.toEntity(nomenclatureDto));
        log.info("DTO object: {} has been saved ", nomenclatureDto);
        return new ResponseEntity<>(NOMENCLATURE_MAPPER.toDto(save), HttpStatus.CREATED);
    }
}
