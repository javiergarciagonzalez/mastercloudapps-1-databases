package es.codeurjc.springrestapiwithdb.controllers.api;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.springrestapiwithdb.dtos.requests.BookRequestDto;
import es.codeurjc.springrestapiwithdb.dtos.requests.CommentRequestDto;
import es.codeurjc.springrestapiwithdb.dtos.responses.BookDetailsResponseDto;
import es.codeurjc.springrestapiwithdb.dtos.responses.BookResponseDto;
import es.codeurjc.springrestapiwithdb.dtos.responses.CommentResponseDto;
import es.codeurjc.springrestapiwithdb.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/")
public class BookControllerRest {

    private BookService bookService;

    public BookControllerRest(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Get all books")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found all books", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BookResponseDto.class))) }) })
    @GetMapping("/")
    public Collection<BookResponseDto> getBooks() {
        return this.bookService.findAll();
    }

    @Operation(summary = "Get a book by its id")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the book", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = BookDetailsResponseDto.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid format id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "Book not found", content = @Content) })
    @GetMapping("/{bookId}")
    public BookDetailsResponseDto getBook(
        @Parameter(description = "id of book to be searched") @PathVariable long bookId) {
        return this.bookService.findById(bookId);
    }

    @Operation(summary = "Create a new book")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Book to be created", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookRequestDto.class)))
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Created the book", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = BookDetailsResponseDto.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid book attributes supplied", content = @Content) })
    @PostMapping("/")
    public BookDetailsResponseDto createBook(@Valid @RequestBody BookRequestDto bookRequestDto) {
        return this.bookService.save(bookRequestDto);
    }

    @Operation(summary = "Add a comment to a book")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "comment to be added", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommentRequestDto.class)))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Added comment to the book", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = CommentResponseDto.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid comment attributes supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "Book not found", content = @Content) })
    @PostMapping("/{bookId}/comments/")
    public CommentResponseDto createComment(
        @Parameter(description = "identifier of the book to which the comment will be added") @PathVariable long bookId,
        @Valid @RequestBody CommentRequestDto commentRequestDto) {
        return this.bookService.addComment(bookId, commentRequestDto);
    }

    @Operation(summary = "Delete a comment from a book")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Deleted comment from the book", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = CommentResponseDto.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid format id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "Book or comment not found", content = @Content) })
    @DeleteMapping("/{bookId}/comments/{commentId}")
    public CommentResponseDto deleteComment(
        @Parameter(description = "identifier of the book to which the comment belongs") @PathVariable long bookId,
        @Parameter(description = "id of comment to be deleted") @PathVariable long commentId) {
        return this.bookService.deleteComment(bookId, commentId);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
        String fieldName = ((FieldError) error).getField();
        String errorMessage = error.getDefaultMessage();
        errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
