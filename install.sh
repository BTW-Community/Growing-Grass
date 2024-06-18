MAPPINGS_VERSION=1.0.3
INTERMEDIARY_FILE_NAME=BTW-CE-Intermediary-Distribution.zip
cd "$PWD"
rm -rdf "build/BTW_dev"
rm -rdf "build/tmp/BTW_dev"
rm -rdf "custom_mappings"
chmod +x ./gradlew
./gradlew --no-daemon downloadAssets
mkdir build/BTW_dev
mkdir custom_mappings
mkdir build/tmp/BTW_dev
unzip -qo mavenRepo/btw/community/mappings/$MAPPINGS_VERSION/mappings-$MAPPINGS_VERSION.jar -d custom_mappings
rm -f ~/.gradle/caches/fabric-loom/1.6.4/minecraft-merged-intermediary.jar
java -jar libs/tiny-remapper-0.8.6+local-fat.jar ~/.gradle/caches/fabric-loom/1.6.4/minecraft-merged.jar ~/.gradle/caches/fabric-loom/1.6.4/minecraft-merged-intermediary.jar ~/.gradle/caches/fabric-loom/1.6.4/intermediary-v2.tiny official intermediary
unzip -qo ./$INTERMEDIARY_FILE_NAME -d build/tmp/BTW_dev
java -jar libs/tiny-remapper-0.8.6+local-fat.jar build/tmp/BTW_dev/BTW-CE-Intermediary.zip build/BTW_dev/BTW-CE-Intermediary.zip custom_mappings/mappings/mappings.tiny intermediary named ~/.gradle/caches/fabric-loom/1.6.4/minecraft-merged-intermediary.jar
unzip -qo ~/.gradle/caches/fabric-loom/minecraftMaven/net/minecraft/minecraft-merged/1.6.4-btw.community.mappings.1_6_4.$MAPPINGS_VERSION-v2/minecraft-merged-1.6.4-btw.community.mappings.1_6_4.$MAPPINGS_VERSION-v2.jar -d build/BTW_dev
unzip -qo build/BTW_dev/BTW-CE-Intermediary.zip -d build/BTW_dev
rm build/BTW_dev/BTW-CE-Intermediary.zip
cd build/BTW_dev
zip -rq ../BTW_dev.zip *
cd ..
rm -rf "BTW_dev"
mkdir "BTW_dev"
mv BTW_dev.zip BTW_dev/BTW_dev.zip
cd ..
mv build/tmp/BTW_dev/BTW-CE-Intermediary-javadoc.jar build/BTW_dev/BTW_dev-javadoc.jar
rm -rf build/tmp/BTW_dev
echo Done!
exit
