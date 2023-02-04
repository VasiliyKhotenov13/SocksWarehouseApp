package me.vasiliy.khotenov.courseprojectapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.vasiliy.khotenov.courseprojectapp.services.SocksService;
import me.vasiliy.khotenov.courseprojectapp.models.SocksColor;
import me.vasiliy.khotenov.courseprojectapp.models.SocksSize;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;


@RestController()
@RequestMapping(path = "/socks")
@Tag(name = "Наименование товара - 'Носки'", description = "CRUD-операции для работы с товаром 'Носки'")
public class SocksController {

    private final SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping("/post")
    @Operation(summary = "Добавление носков на склад")
    public ResponseEntity<String> incoming(@PathParam("socksColor") SocksColor socksColor,
                                           @PathParam("socksSize") SocksSize socksSize,
                                           @PathParam("cottonPart") int cottonPart,
                                           @PathParam("quantity") int quantity) {
        {
            if (socksService.addToStorage(socksColor, socksSize, cottonPart, quantity)) {
                return ResponseEntity.status(HttpStatus.OK).body("Новая партия носков добавлена на склад");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Не удалось добавить новую партию носков на склад!");
        }
    }

    @GetMapping("/cotton/min")
    @Operation(summary = "Получение сведений о наличии товара на складе по содержанию хлопка меньше указанного")
    public ResponseEntity<String> findCottonPartMin(@PathParam("socksColor") SocksColor socksColor,
                                                    @PathParam("socksSize") SocksSize socksSize,
                                                    @PathParam("cottonMin") int cottonMin) {
        int quantity = socksService.findByCottonPartLessThan(socksColor, socksSize, cottonMin);
        if (quantity > 0) {
            return ResponseEntity.ok().body("По Вашему запросу найдено " + quantity + " шт. товара");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("По Вашему запросу ничего не найдено!");
        }
    }

    @GetMapping("/cotton/max")
    @Operation(summary = "Получение сведений о наличии товара на складе по содержанию хлопка больше указанного")
    public ResponseEntity<String> findCottonPartMax(@PathParam("socksColor") SocksColor socksColor,
                                                    @PathParam("socksSize") SocksSize socksSize,
                                                    @PathParam("cottonMax") int cottonMax) {
        int quantity = socksService.findByCottonPartMoreThan(socksColor, socksSize, cottonMax);
        if (quantity > 0) {
            return ResponseEntity.ok().body("По Вашему запросу найдено " + quantity + " шт. товара");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("По Вашему запросу ничего не найдено!");
        }
    }

    @PutMapping("/put")
    @Operation(summary = "Отпуск со склада партии товара (носков)")
    public ResponseEntity<String> outgoing(@PathParam("socksColor") SocksColor socksColor,
                                           @PathParam("socksSize") SocksSize socksSize,
                                           @PathParam("cottonPart") int cottonPart,
                                           @PathParam("quantity") int quantity) {
        if (socksService.releaseFromStorage(socksColor, socksSize, cottonPart, quantity)) {
            return ResponseEntity.ok().body("Товар отправлен со склада.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Товар не отправлен. Недостаточное количество товара или полное отсутствие!");
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Удаление бракованной партии товара (носков)")
    public ResponseEntity<String> cancellation(@PathParam("socksColor") SocksColor socksColor,
                                               @PathParam("socksSize") SocksSize socksSize,
                                               @PathParam("cottonPart") int cottonPart,
                                               @PathParam("quantity") int quantity) {
        if (socksService.delete(socksColor, socksSize, cottonPart, quantity)) {
            return ResponseEntity.ok().body("Бракованный товар списан со склада.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Данная партия носков не найдена!");
    }
}
