package accommodation;

import accommodation.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{

    @Autowired
    RoomInfoRepository roomInfoRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverSave_RoomInfo(@Payload RoomConditionChanged roomConditionChanged){
        if(roomConditionChanged.isMe()){
            System.out.println("##### listener 객실정보저장 : " + roomConditionChanged.toJson());
            RoomInfo roomInfo = new RoomInfo();
            roomInfo.setRoomNumber(roomConditionChanged.getRoomNumber());
            roomInfo.setRoomStatus(roomConditionChanged.getRoomStatus());
            roomInfo.setRoomName(roomConditionChanged.getRoomName());
            roomInfoRepository.save(roomInfo);

            // external message send
            System.out.println("##### ");
            System.out.println("##### external message send (room regist) : " + roomConditionChanged.toJson());
            System.out.println("##### ");
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverSave_CheckOuted(@Payload CheckedOut checkedOut){
        if(checkedOut.isMe()){
            // external message send
            System.out.println("##### ");
            System.out.println("##### external message send (checkout) : " + checkedOut.toJson());
            System.out.println("##### ");
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverSave_PaymentCompleted(@Payload PaymentCompleted paymentCompleted){
        if(paymentCompleted.isMe()){
            // external message send
            System.out.println("##### ");
            System.out.println("##### external message send (payment) : " + paymentCompleted.toJson());
            System.out.println("##### ");
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverSave_Reserved(@Payload Reserved reserved) {
        if (reserved.isMe()) {
            // external message send
            System.out.println("##### ");
            System.out.println("##### external message send (reserved) : " + reserved.toJson());
            System.out.println("##### ");
        }
    }
}
