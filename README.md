Bu proje, Spring Boot kullanılarak geliştirilen ölçeklenebilir ve modüler bir backend uygulamasıdır.

✅ **RESTful API Desteği:** JSON tabanlı API ile hızlı ve güvenli veri alışverişi sağlar.  
✅ **Veritabanı Entegrasyonu:** Spring Data JPA ve Hibernate kullanarak PostgreSQL veritabanı ile entegrasyon sağlanır.  
✅ **Spring Validation ve Service Katmanında Kontroller:** API uç noktalarına gelen veriler, hem Spring Validation hem de service katmanında yapılan ek kontrollerle doğrulanacaktır. Spring Validation, verilerin iş kurallarına uygunluğunu ve doğruluğunu kontrol ederken, service katmanında daha karmaşık iş mantıkları ve özel kurallar da dikkate alınacaktır. Bu sayede, geçerli olmayan verilerin işleme alınması engellenecek ve API'nin sağlamlığı artırılacaktır.  
✅ **Dependency Injection (Bağımlılık Enjeksiyonu)** olarak Constructor Injection yöntemi kullanılmaktadır. Bu yaklaşım, sınıfların ihtiyaç duyduğu bağımlılıkların constructor (yapıcı) metoduna parametre olarak enjekte edilmesini sağlar.  
✅ **Hata Yönetimi:** GlobalExceptionHandler sınıfı kullanılarak, uygulamada meydana gelen hatalar merkezi bir şekilde yakalanacak ve belirli bir formatta JSON yanıtları döndürülecektir. Bu, kullanıcı dostu hata mesajları ve hata kodları ile API'nin tutarlı bir şekilde hata yönetimini sağlar.  
✅ **Güvenlik:** Spring Security ile temel kimlik doğrulama ve yetkilendirme mekanizmaları sağlanır.
✅ **Dokümantasyon:** Swagger ile API uç noktaları kolayca keşfedilebilir. Swagger dokümantasyonu, ilerleyen sürümlerde projeye eklenecektir.  
✅ **Lombok Kullanımı:** Proje, Java kodlarını daha temiz ve okunabilir hale getirmek için Lombok kütüphanesini kullanmaktadır.

## Test Otomasyonu
RestAssured ile projedeki API uç noktaları kapsamlı bir şekilde test edilecektir. API uç noktalarına yapılan isteklerin doğru yanıtları verip vermediği, happy path (olumlu senaryolar), negatif senaryolar (hatalı istekler) ve iş kuralları (doğru veri formatı, sınır değerleri, vb.) göz önünde bulundurularak test edilecektir.

### Testler, aşağıdaki kriterlere odaklanacaktır:

**Happy Path:** Geçerli verilerle yapılan isteklerde, API'nin beklenen doğru yanıtı verdiği doğrulanacaktır.  
**Negatif Senaryolar:** Geçersiz veri veya hatalı isteklerle yapılan testlerde, API'nin uygun hata mesajlarını ve hata kodlarını döndürdüğü kontrol edilecektir.  
**Kimlik Doğrulama ve Yetkilendirme:** API uç noktalarına erişim için doğru kimlik doğrulama ve yetkilendirme mekanizmalarının düzgün çalıştığı test edilecektir. Yetkisiz erişimler ve rol bazlı izinler test edilecektir.

Bu testler sayesinde, API'nin hem beklenen koşullar altında hem de beklenmeyen durumlar altında doğru, güvenli ve güvenilir şekilde çalıştığı doğrulanacaktır.
