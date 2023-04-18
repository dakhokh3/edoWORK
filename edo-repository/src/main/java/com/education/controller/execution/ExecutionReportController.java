package com.education.controller.execution;

import com.education.service.execution.ExecutorReportService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import model.dto.ExecutionReportDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.education.mapper.ExecutionReportMapper.EXECUTION_REPORT_MAPPER;

/**
 * @author Nikita Sheikin
 * REST контроллер принимает ExecutionReportDto из edo-service
 * для сохрания в бд
 */
@Log4j2
@RestController
@RequestMapping("/api/repository/report")
@AllArgsConstructor
@ApiOperation("ExecutionReportController в модуле edo-repository")
public class ExecutionReportController {
    private final ExecutorReportService reportService;

    @PostMapping
    public ResponseEntity<ExecutionReportDto> submitReport(@RequestBody ExecutionReportDto reportDto) {
        log.info("Отправляем reportDto в service для сохранения в БД");
        var report = reportService.submitReport(EXECUTION_REPORT_MAPPER.toEntity(reportDto));
        log.info("{} был сохранен", reportDto);
        return new ResponseEntity<>(EXECUTION_REPORT_MAPPER.toDto(report), HttpStatus.CREATED);
    }
}
