pragma solidity ^0.4.0;
contract EnquiryDetails { 
    struct EnquiryDetailsStruct {
         uint transactionId;
         uint transactionSequenceId;
         string transactionStatus;
         string fromBank;
         string toBank;
         string payloadDataHash;
    }
   
   EnquiryDetailsStruct public enq;
   
   event Enquiry (uint trnId, uint trnSeq, string trnSts, string frmBank, string tBank, string pyldHsh);
   
   function storeEnquiry(uint transactionId,
         uint transactionSequenceId,
         string transactionStatus,
         string fromBank,
         string toBank, string payloadDataHash) public returns (uint result){
             
             emit Enquiry(transactionId, transactionSequenceId, transactionStatus, fromBank, toBank,payloadDataHash);
             
             enq.transactionId = transactionId;
             enq.transactionSequenceId = transactionSequenceId;
             enq.transactionStatus = transactionStatus;
             enq.fromBank = fromBank;
             enq.toBank = toBank;
             enq.payloadDataHash = payloadDataHash;
             return 1;
    }
    
    function getEnquiryDetails() public constant returns (uint transactionId,
         uint transactionSequenceId,
         string transactionStatus,
         string fromBank,
         string toBank, string payloadDataHash) {
             return (enq.transactionId, enq.transactionSequenceId, enq.transactionStatus,
             enq.fromBank, enq.toBank,enq.payloadDataHash);
    }
      
}
