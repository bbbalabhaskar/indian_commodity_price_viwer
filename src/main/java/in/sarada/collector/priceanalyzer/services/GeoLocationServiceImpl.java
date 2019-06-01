package in.sarada.collector.priceanalyzer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class GeoLocationServiceImpl implements GeoLocationService {

    @Value("${opencage.data.url}")
    private String opencageDataUrl;

    @Autowired
    private HttpClient httpClient;

    @Override
    //@Scheduled(fixedRate = 86400000)
    public void initLocationCollection() {

        String[] markets = {"Cachar", "Cachar", "Gauripur", "Karimganj", "Dhing", "Kasdol", "Champa", "Charama", "Lakhanpuri", "Narharpur", "Raigarh", "Savarkundla", "Anand", "Anand(Veg,Yard,Anand)", "Khambhat(Veg Yard Khambhat)", "Deesa(Bhildi)", "Deesa(Deesa Veg Yard)", "Palanpur", "Tharad", "Tharad(Rah)", "Vadgam", "Jambusar", "Jambusar(Kaavi)", "Davgadbaria(Piplod)", "Devgadhbaria", "Mansa(Manas Veg Yard)", "Bhanvad", "Manavdar", "Talalagir", "Visavadar", "Bachau", "Unava", "Vankaner(Sub yard)", "Rajpipla", "Sami", "Dhoraji", "Rajkot", "Rajkot(Ghee Peeth)", "Himatnagar", "Meghraj", "Meghraj(Radlavada)", "Modasa", "Modasa(Tintoi)", "Vadali", "Surat", "Chotila", "Halvad", "Limdi", "Vadhvan", "Savli", "Chikli(Khorgam)", "Mullana", "Shahzadpur", "Faridabad", "Ratia", "Iamailabad", "Pehowa", "Mohindergarh", "New Grain Market , Panchkula", "Panchkul(Kalka)", "Samalkha", "Meham", "kalanwali", "Gohana", "Chhachrauli", "Yamuna Nagar", "Bilaspur", "Chamba", "Kangra(Baijnath)", "Kangra(Jaisinghpur)", "Palampur", "Kullu", "Mandi(Mandi)", "Nahan", "Paonta Sahib", "Zaloosa-Chararishrief (F&V)", "Akhnoor", "Parimpore", "Udhampur", "Gadhwah", "Giridih", "Bangalore", "Kanakapura", "Ramanagara", "Kudchi", "Channagiri", "Honnali", "Holenarsipura", "Kolar", "Kustagi", "Hunsur", "Piriya Pattana", "Santhesargur", "Manvi", "Madhugiri", "Alappuzha", "Chengannur", "Kayamkulam", "Madhavapuram", "Mannar", "Angamaly", "Ernakulam", "Kothamangalam", "Perumbavoor", "Thrippunithura", "Thodupuzha", "Irikkur", "Irityy", "Kannur", "Payyannur", "Chathanoor", "Kanjirappally", "Thalayolaparambu", "Kallachi", "Mukkom", "Palayam", "Quilandy", "Kottakkal", "Pattambi", "Kuttoor", "Omalloor", "Kodungalloor", "Anyara(EEC)", "Attingal", "Neyyatinkara", "Kalpetta", "Manathavady", "Pulpally", "Alirajpur(F&V)", "Ashoknagar", "Ashoknagar(F&V)", "Mungawali", "Khategaon", "Kukshi", "Dindori", "Babai", "Sanwer", "Thandla", "Khandwa(F&V)", "Khujner", "Momanbadodiya", "Singroli", "Ujjain(F&V)", "Shrirampur", "Partur", "Jalgaon(Masawat)", "Kalmeshwar", "Kamthi", "Kinwat", "Chandvad", "Lasalgaon", "Yeola", "Pune(Khadiki)", "Pune(Pimpri)", "Alibagh", "Murud", "Karad", "Patan", "Vai", "Akluj", "Bhivandi", "Murbad", "Ulhasnagar", "Mokokchung Town", "Fish,Poultry & Egg Market, Gazipur", "Jaleswar", "Nilagiri", "Bargarh", "Bargarh(Barapalli)", "Godabhaga", "Bolangir", "Kantabaji", "Tusura", "Boudh", "Khunthabandha", "Dhenkanal", "Hindol", "Kamakhyanagar", "Mottagaon", "Kasinagar", "Parlakhemundi", "Jagatsinghpur", "Jajpur", "Anandapur", "Jeypore", "Jeypore(Kotpad)", "Koraput", "Koraput(Semilguda)", "Malkanagiri", "Malkangiri(Korakunda)", "Betnoti", "Chuliaposi", "Bahadajholla", "Sarankul", "Kuchinda", "Dungurapalli", "Pandkital", "Bonai", "Panposh", "Sargipali", "Talwandi Sabo", "Garh Shankar", "Garh Shankar(Mahalpur)", "GarhShankar (Kotfatuhi)", "Goraya", "Kapurthala", "Raikot", "Sahnewal", "Lalsot", "Jalore", "Sanchor", "Jodhpur(Grain)(Bhagat Ki Kothi)", "Sri Madhopur", "Surajgarh", "Karamadai", "Pongalur", "Namagiripettai", "Namakkal", "Rasipuram", "Tiruchengode", "Velur", "Bhainsa", "Indravelly(Utnoor)", "Khanapur", "Mahboob Manison", "Choppadandi", "Huzzurabad", "Jammikunta", "Karimnagar", "Sattupalli", "Wyra", "Wanaparthy town", "Chandur(Mungodu)", "Huzurnagar", "Nakrekal", "Nalgonda", "Neredcherla", "Tirumalagiri", "Venkateswarnagar(Chintapalli)", "Bodhan", "Pitlam", "Pargi", "Agra", "Fatehabad", "Samsabad", "Aligarh", "Atrauli", "Khair", "Jasra", "Sirsa", "Akbarpur", "Auraiya", "Badayoun", "Bilsi", "Dataganj", "Shahaswan", "Ujhani", "Baraut", "Mihipurwa", "Naanpara", "Risia", "Ruperdeeha", "Ballia", "Chitwadagaon", "Vilthararoad", "Panchpedwa", "Tulsipur", "Utraula", "Rudauli", "Anwala", "Bahedi", "Bareilly", "Richha", "Basti", "Gopiganj", "Bijnaur", "Chaandpur", "Najibabad", "Buland Shahr", "Gulavati", "Jahangirabad", "Khurja", "Sikarpur", "Siyana", "Karvi", "Mau(Chitrakut)", "Barhaj", "Devariya", "Awagarh", "Etah", "Kasganj", "Bharthna", "Jasvantnagar", "Kamlaganj", "Kayamganj", "Mohamadabad", "Fatehpur", "Kishunpur", "Firozabad", "Shikohabad", "Sirsaganj", "Tundla", "Dadri", "Dankaur", "Javer", "Ghaziabad", "Hapur", "Gazipur", "Jamanian", "Gonda", "Karnailganj", "Sehjanwa", "Bharuasumerpur", "Hardoi", "Sandila", "Shahabad(New Mandi)", "Haathras", "Shadabad", "Sikandraraau", "Ait", "Kadaura", "Kalpi", "Madhogarh", "Mugrabaadshahpur", "Baruwasagar", "Jhansi", "Hasanpur", "Chhibramau(Kannuj)", "Kannauj", "Pukhrayan", "Rura", "Golagokarnath", "Lakhimpur", "Paliakala", "Lalitpur", "Mehrauni", "Anandnagar", "Gadaura", "Maharajganj", "Nautnava", "Mahoba", "Bewar", "Mainpuri", "Kosikalan", "Mathura", "Kopaganj", "Bhehjoi", "Chandausi", "Muradabad", "Sambhal", "Khatauli", "Muzzafarnagar", "Thanabhawan", "Tamkuhi Road", "Billsadda", "Pilibhit", "Vishalpur", "Bachranwa", "Jayas", "Lalganj", "Raibareilly", "Salon", "Milak", "Rampur", "Chutmalpur", "Gangoh", "Nakud", "Saharanpur", "Jalalabad", "Tilhar", "Payagpur", "Naugarh", "Sahiyapur", "Hargaon (Laharpur)", "Maholi", "Sindholi", "Sitapur", "Viswan", "Bangarmau", "Unnao", "Dehradoon", "Rishikesh", "Lakshar", "Ramnagar", "Bazpur", "Gadarpur", "Jaspur(UC)", "Nanakmatta", "Rudrapur", "Bishnupur(Bankura)", "Khatra", "Asansol", "Durgapur", "Falakata", "Samsi", "Chakdah", "Habra"};

        for (String market : markets) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(String.format(opencageDataUrl, market)))
                    .timeout(Duration.ofMinutes(1))
                    .header("Content-Type", "application/json")
                    .build();
            httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(stringHttpResponse -> {
                        if (stringHttpResponse.statusCode() == 200) {
                            return stringHttpResponse.body();
                        } else {
                            return "";
                        }
                    }).thenAccept(s -> {
                if (!StringUtils.isEmpty(s)) {
                    //TODO save the location data.
                }
            }).join();
        }

    }


}
