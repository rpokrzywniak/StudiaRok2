lacznaIlosc=0

for var in "$@"
do
ilosc=$(find $var -type f | wc -l)
lacznaIlosc=$(($suma+$ilosc))
done

echo $lacznaIlosc
