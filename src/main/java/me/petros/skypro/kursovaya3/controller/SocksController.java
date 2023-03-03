package me.petros.skypro.kursovaya3.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.petros.skypro.kursovaya3.model.Color;
import me.petros.skypro.kursovaya3.model.Size;
import me.petros.skypro.kursovaya3.model.Socks;
import me.petros.skypro.kursovaya3.service.SocksServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/socks")
@Tag(name= "Склад носков", description = "CRUD-операции и другие эндпоинты для работы c носками")
public class SocksController {
  private final SocksServices socksServices;

    public SocksController(SocksServices socksServices) {
        this.socksServices = socksServices;
    }
    @PostMapping
    @Operation(summary = "Добавление товара",
               description = "Регистрирует приход носков на склад со всеми характеристиками")
    @Parameters(value = {@Parameter(name = "color", example = "WHITE"),
                         @Parameter(name = "size", example="S"),
                         @Parameter(name = "cottonPart", example="100"),
                         @Parameter(name = "quantity", example="5")})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Удалось добавить приход"),
                           @ApiResponse(responseCode = "400",
                                        description ="Товара нет на складе в нужном количестве или параметры запроса имеют некорректный формат" ),
                           @ApiResponse(responseCode = "500", description ="Произошла ошибка, не зависящая от вызывающей стороны.",
                                   content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Socks.class))
                    )
            }
            )
    }
    )
    public ResponseEntity<Integer> addSocks(@Valid@RequestBody Socks socks){
       int id= socksServices.addSocks(socks);
        return ResponseEntity.ok().body(id);
    }
    @DeleteMapping("/delete")
    @Operation(summary = "Ликвидация бракованного товара",
               description = "Регистрирует списание испорченных (бракованных) носков")
    @Parameters(value = {@Parameter(name = "color", example = "WHITE"),
                         @Parameter(name = "size", example="S"),
                         @Parameter(name = "cottonPart", example="100"),
                         @Parameter(name = "quantity", example="5")})

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Запрос выполнен, товар списан со склада"),
                           @ApiResponse(responseCode = "400", description ="Параметры запроса отсутствуют или имеют некорректный формат" ),
                           @ApiResponse(responseCode = "500", description ="Произошла ошибка, не зависящая от вызывающей стороны.",
                            content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Socks.class))
                            )
                    }
            )
    }
    )
    public ResponseEntity<Void> deleteSocksDefect(@Valid@RequestBody Socks socks){
        if (socksServices.deleteSocksDefective(socks)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/sell")
    @Operation(summary = "Отгрузка проданных товаров")
    @Parameters(value = {@Parameter(name = "color", example = "WHITE"),
                         @Parameter(name = "size", example="S"),
                         @Parameter(name = "cottonPart", example="100"),
                         @Parameter(name = "quantity", example="5")})

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Запрос выполнен, товар списан со склада"),
                           @ApiResponse(responseCode = "400", description ="Параметры запроса отсутствуют или имеют некорректный формат" ),
                           @ApiResponse(responseCode = "500", description ="Произошла ошибка, не зависящая от вызывающей стороны.",
                            content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Socks.class))
                            )
                    }
            )
    }
    )
    public ResponseEntity<Void> sellSocks(@Valid@RequestBody Socks socks){
        if (socksServices.deleteSocksDefective(socks)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/info")
    @Operation(summary = "Поиск товаров",
               description = "Возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса")
    @Parameters(value = {@Parameter(name = "color", example = "WHITE"),
                         @Parameter(name = "size", example="S"),
                         @Parameter(name = "cottonPart", example="100")})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Запрос выполнен"),
                           @ApiResponse(responseCode = "400", description ="Параметры запроса отсутствуют или имеют некорректный формат" ),
                           @ApiResponse(responseCode = "500", description ="Произошла ошибка, не зависящая от вызывающей стороны.",
                            content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Socks.class))
                            )
                    }
            )
    }
    )
    public Integer getSocksByParameter(@RequestParam(required = false, name = "size") Size size,
                                       @RequestParam(required = false, name = "color") Color color,
                                       @RequestParam(required = false, name = "cottonMin")Integer cottonMin,
                                       @RequestParam(required = false, name = "cottonMax")Integer cottonMax){
       return socksServices.getSocks(size,color,cottonMin,cottonMax);
    }
}
