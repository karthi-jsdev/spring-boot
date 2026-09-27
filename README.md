# spring-boot

1. https://start.spring.io/
	spring initialiser to start the project
2. create controller package- 
		annotations
		@RestController
		@RequestMappig('/api/user');
		
		@GetMapping()		
		
3. dev dependency to run automatically like nodemon
	spring-boot-devtools
	RUN TIME
	add in the pom.xml
	
4. Create Model package- 
		name,id right click generate getter and setter
		right click create constructor

5. Mysql install community edition

6. in google search - maven spring boot starter jpa
	click on the version
	add in the pom.xml

7.  in google search - spring boot mysql application.properties
	add in application.properties

8. maven mysql connector	
	add the dependency in pom.xml
	
9. entity -> ORM
		create new Entity package
		@Entity
		@Table(name = "users")	
	inside class 
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	generate getter and setters
	
10. Repository	package
	repository interface used for database operations
	create interface userRepository extends jparepository
	
11. in controller add annotaion for repository
		@Autowired
		
12. in controller create user		
	@PostMapping
	RequestBody UserEntity user
	@PostMapping
	public UserEntity createUser(@RequestBody UserEntity user) {
		return userRepository.save(user);
	}
13. Get single User
	@GetMapping("/{id}")	
	@PathVariable
	@GetMapping("/{id}")
	public Optional<UserEntity> getUserById(@PathVariable Long id){
		return userRepository.findById(id);
	}
	
14. UserHandling Error - No data
		Create Exceptions Package
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public class ResourceNotFoundException extends RuntimeException{
		public ResourceNotFoundException(String messsage) {
			super(messsage);
		}
	}
	
15. update user
		@PutMapping("/{id}")
	public UserEntity updateUser(@PathVariable Long id,@RequestBody UserEntity user) {
		UserEntity userData = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found in this id"+id));
		userData.setEmail(user.getEmail());
		userData.setName(user.getName());
		return userRepository.save(userData);
	}
	
16. Delete User
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		UserEntity userData = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found in this id"+id));
		userRepository.delete(userData);
		return ResponseEntity.ok().build();
	}
	
17. maven springdoc openai starter	
	swagger api documentation
18. 	
	