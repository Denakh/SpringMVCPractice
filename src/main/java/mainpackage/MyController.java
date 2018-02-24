package mainpackage;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/")
public class MyController {

    private Map<Long, byte[]> photos = new HashMap();

    @RequestMapping("/")
    public String onIndex() {
        return "index";
    }

    @RequestMapping("/photo/{photo_id}")
    public ResponseEntity<byte[]> onPhoto(@PathVariable("photo_id") long id) {
        return photoById(id);
    }

    @RequestMapping(value = "/add_photo", method = RequestMethod.POST)
    public String onAddPhoto(Model model, @RequestParam MultipartFile photo) {
        if (photo.isEmpty())
            throw new PhotoErrorException();
        try {
            long id = System.currentTimeMillis();
            photos.put(id, photo.getBytes());
            String answer = "";
            Set<Long> idset = photos.keySet();
            for (long idp : idset) {
                answer += "<tr><td><input type='checkbox' name='a' value=" + idp + "></td><td>" + idp +
                        "</td><td><img src='/photo/" + idp + "'></td></tr>";
            }
            model.addAttribute("phototable", answer);
            return "resultalt";
        } catch (IOException e) {
            throw new PhotoErrorException();
        }
    }

    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public String onView(Model model) {
        try {
            String answer = "";
            Set<Long> idset = photos.keySet();
            for (long idp : idset) {
                answer += "<tr><td><input type='checkbox' name='a' value=" + idp + "></td><td>" + idp +
                        "</td><td><img src='/photo/" + idp + "'></td></tr>";
            }
            model.addAttribute("phototable", answer);
            return "resultalt";
        } catch (Exception e) {
            throw new PhotoErrorException();
        }
    }

    @RequestMapping(value = "/deletephotos", method = RequestMethod.POST)
    public String onDeletePhotos(@RequestParam("a") String idlist) {
        if (idlist == null || idlist.equals("")) throw new PhotoErrorException();
        String[] ida = idlist.split(",");
        List<Long> idl = new ArrayList<>();
        for (String ids : ida) {
            idl.add(Long.parseLong(ids));
        }
        for (long idp : idl) photos.remove(idp);
        return "index";
    }

    private ResponseEntity<byte[]> photoById(long id) {
        byte[] bytes = photos.get(id);
        if (bytes == null)
            throw new PhotoNotFoundException();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    }

}
