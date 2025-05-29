package com.levi9.internship.controller;
import com.levi9.internship.model.DeleteResponse;
import com.levi9.internship.model.OrderDTO;
import com.levi9.internship.model.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;
import java.util.List;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-28T09:23:59.587130300+02:00[Europe/Budapest]")
@Validated
@Tag(name = "users", description = "the users API")
public interface UsersApi {

    /**
     * POST /users : Create User
     * Create User and save it into the DB
     *
     * @param userDTO User data (optional)
     * @return Successful operation (status code 201)
     *         or Provided data is not acceptable! (status code 400)
     *         or Conflict error - duplicate unique value! (status code 409)
     */
    @Operation(
            operationId = "createUser",
            summary = "Create User",
            description = "Create User and save it into the DB",
            tags = { "users" },
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successful operation", content = {
                            @Content(mediaType = "application/json; charset=UTF-8", schema = @Schema(implementation = UserDTO.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Provided data is not acceptable!"),
                    @ApiResponse(responseCode = "409", description = "Conflict error - duplicate unique value!")
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/users",
            produces = { "application/json; charset=UTF-8" },
            consumes = { "application/json; charset=UTF-8" }
    )
    ResponseEntity<UserDTO> createUser(
            @Parameter(name = "UserDTO", description = "User data") @Valid @RequestBody(required = false) UserDTO userDTO
    );


    /**
     * DELETE /users/delete/{username} : Delete User
     * Delete user from DB
     *
     * @param username Username of an entity (required)
     * @return Successful operation (status code 200)
     *         or Provided data is not acceptable! (status code 400)
     *         or Requested document is not found! (status code 404)
     *         or Internal server error! (status code 500)
     */
    @Operation(
            operationId = "deleteUserByUsername",
            summary = "Delete User",
            description = "Delete user from DB",
            tags = { "users" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                            @Content(mediaType = "application/json; charset=UTF-8", schema = @Schema(implementation = DeleteResponse.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Provided data is not acceptable!"),
                    @ApiResponse(responseCode = "404", description = "Requested document is not found!"),
                    @ApiResponse(responseCode = "500", description = "Internal server error!")
            }
    )
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/users/delete/{username}",
            produces = { "application/json; charset=UTF-8" }
    )
    ResponseEntity<DeleteResponse> deleteUserByUsername(
            @Parameter(name = "username", description = "Username of an entity", required = true, in = ParameterIn.PATH) @PathVariable("username") String username
    );


    /**
     * GET /users/{userId}/orders : Get All Orders by provided UserId
     * Get all Orders that are available in the system
     *
     * @param userId UserId of an entity (required)
     * @return Successful operation (status code 200)
     */
    @Operation(
            operationId = "getOrdersByUserId",
            summary = "Get All Orders by provided UserId",
            description = "Get all Orders that are available in the system",
            tags = { "users" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                            @Content(mediaType = "application/json; charset=UTF-8", array = @ArraySchema(schema = @Schema(implementation = OrderDTO.class)))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/users/{userId}/orders",
            produces = { "application/json; charset=UTF-8" }
    )
    ResponseEntity<List<OrderDTO>> getOrdersByUserId(
            @Parameter(name = "userId", description = "UserId of an entity", required = true, in = ParameterIn.PATH) @PathVariable("userId") String userId
    );


    /**
     * GET /users/id/{userId} : Get user by userId
     * Get user by provided userId from DB
     *
     * @param userId UserId of an entity (required)
     * @return Successful operation (status code 200)
     *         or Provided data is not acceptable! (status code 400)
     *         or Requested document is not found! (status code 404)
     *         or Internal server error! (status code 500)
     */
    @Operation(
            operationId = "getUserById",
            summary = "Get user by userId",
            description = "Get user by provided userId from DB",
            tags = { "users" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                            @Content(mediaType = "application/json; charset=UTF-8", schema = @Schema(implementation = UserDTO.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Provided data is not acceptable!"),
                    @ApiResponse(responseCode = "404", description = "Requested document is not found!"),
                    @ApiResponse(responseCode = "500", description = "Internal server error!")
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/users/id/{userId}",
            produces = { "application/json; charset=UTF-8" }
    )
    ResponseEntity<UserDTO> getUserById(
            @Parameter(name = "userId", description = "UserId of an entity", required = true, in = ParameterIn.PATH) @PathVariable("userId") String userId
    );


    /**
     * GET /users/username/{username} : Get user by Username
     * Get user by provided username from DB
     *
     * @param username Username of an entity (required)
     * @return Successfully created (status code 201)
     *         or Provided data is not acceptable! (status code 400)
     *         or Requested document is not found! (status code 404)
     *         or Internal server error! (status code 500)
     */
    @Operation(
            operationId = "getUserByUsername",
            summary = "Get user by Username",
            description = "Get user by provided username from DB",
            tags = { "users" },
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successfully created", content = {
                            @Content(mediaType = "application/json; charset=UTF-8", schema = @Schema(implementation = UserDTO.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Provided data is not acceptable!"),
                    @ApiResponse(responseCode = "404", description = "Requested document is not found!"),
                    @ApiResponse(responseCode = "500", description = "Internal server error!")
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/users/username/{username}",
            produces = { "application/json; charset=UTF-8" }
    )
    ResponseEntity<UserDTO> getUserByUsername(
            @Parameter(name = "username", description = "Username of an entity", required = true, in = ParameterIn.PATH) @PathVariable("username") String username
    );


    /**
     * GET /users : Get All Users
     * Get all users that are available in the system
     *
     * @return Successful operation (status code 200)
     */
    @Operation(
            operationId = "getUsers",
            summary = "Get All Users",
            description = "Get all users that are available in the system",
            tags = { "users" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                            @Content(mediaType = "application/json; charset=UTF-8", array = @ArraySchema(schema = @Schema(implementation = UserDTO.class)))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/users",
            produces = { "application/json; charset=UTF-8" }
    )
    ResponseEntity<List<UserDTO>> getUsers(

    );

}
